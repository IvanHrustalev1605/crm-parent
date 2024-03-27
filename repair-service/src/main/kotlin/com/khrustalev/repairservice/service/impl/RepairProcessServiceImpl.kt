package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.FullInfoRepairDto
import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.RepairProcessDto
import com.khrustalev.repairservice.dto.enums.RepairProcessState
import com.khrustalev.repairservice.feign.RepairPartsServiceFeignClient
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Service
class RepairProcessServiceImpl(private val storageFeignClient: StorageFeignClient,
                               private val carRepairStateService: CarRepairStateService,
                               private val telegramService: TelegramService,
                               private val carArrivalStateService: CarArrivalStateService,
                               private val repairPartsServiceFeignClient: RepairPartsServiceFeignClient,
                               private val longRepairService: LongRepairService,
                               private val carLongRepairStateService: CarLongRepairStateService) : RepairProcessService {
    private val LOGGER: Logger = LoggerFactory.getLogger(RepairProcessServiceImpl::class.java)

    override fun createNewRepairProcess(repairInfoDto: RepairInfoDto, repairRequestList: MutableList<Long>): RepairProcessDto? {
        val arrivalStateDto = carArrivalStateService.getActualArrivalStateByCarId(repairInfoDto.carId!!)
        if (arrivalStateDto != null) {
            if (!arrivalStateDto.repairRequestWritten) {
                throw Exception("Заявка на ремонт не написана или не согласована. Нельзя приступить к ремонту!")
            }
            val repairProcessDto = RepairProcessDto()
            repairProcessDto.repairStartAt = LocalDateTime.now()
            repairProcessDto.carId = repairInfoDto.carId
            repairProcessDto.repairRequestIds = repairRequestList
            repairProcessDto.createTime = LocalDateTime.now()
            if (!arrivalStateDto.needLongRepair!!) {
                repairProcessDto.endRepair = LocalDateTime.now().plusHours(24)
                repairProcessDto.repairProcessState = RepairProcessState.NEW
                repairProcessDto.carRepairStatesIds = mutableListOf(carRepairStateService.createNewRepairState(repairInfoDto))
                val saveRepairProcess = storageFeignClient.saveRepairProcess(repairProcessDto)
                return if (saveRepairProcess > 0) {
                    telegramService.sendMessage("СООБЩЕНИЕ ДЛЯ МЕХАНИКОВ: ${repairInfoDto.mechanicIds} /*для каждого будет отсылаться отдельное сообщение*/  \n" +
                            "[Подтвердите взятие машины в ремонт](http://localhost:8080/api/rest/repair-service/take-to-repair-process?repairProcessId=$saveRepairProcess) \uD83D\uDD95")
                    LOGGER.info("Успешно сохранили repairProcess")
                    repairProcessDto
                } else {
                    LOGGER.info("При сохранение repairProcess произошла ошибка!")
                    null
                }
            } else {
                LOGGER.info("Требуется длительный процесс ремонта. Начинаем создание...")
                repairProcessDto.repairProcessState = RepairProcessState.NEED_LONG_REPAIR
                val saveRepairProcess = storageFeignClient.saveRepairProcess(repairProcessDto)
                val result = longRepairService.createNewLongRepairProcess(repairInfoDto, saveRepairProcess)
                if (result != null) {
                    telegramService.sendMessage("СООБЩЕНИЕ ДЛЯ МЕХАНИКОВ: ${repairInfoDto.mechanicIds} /*для каждого будет отсылаться отдельное сообщение*/ Создан длительный ремонт для машины с ID ${repairInfoDto.carId} " +
                            "Пожалуйста, примите его в работу\uD83D\uDD95")
                    LOGGER.info("Длительный ремонт начат успешно...")
                    return repairProcessDto
                }
                throw Exception("Не удалось начать процесс длительного ремонта")
            }
        }
            throw Exception("Машина вообще не заезжала на базу. Требуется уточнение данных!")
        }

    override fun getAllRepairs(): MutableList<FullInfoRepairDto> {
        val fastRepairsList = storageFeignClient.getAllFastRepairProcess()
        val longRepairsList = storageFeignClient.getAllLongRepairProcess()
        var result: MutableList<FullInfoRepairDto> = mutableListOf()
        fastRepairsList.forEach {f ->
            var fullInfoRepairDto = FullInfoRepairDto()
            fullInfoRepairDto.repairDro = f
            fullInfoRepairDto.longRepairProcessDto = longRepairsList.stream().filter{it.repairId == f.id}.findFirst().getOrNull()
            result.add(fullInfoRepairDto)
        }
        return result
    }

    override fun takeRepairToWork(repairProcessId: Long): Boolean {
        val processDto = storageFeignClient.getRepairProcessById(repairProcessId)
        processDto.repairProcessState = RepairProcessState.IN_REPAIR
        processDto.actual = true
        return if (storageFeignClient.saveRepairProcess(processDto) > 0) {
            telegramService.sendMessage("Статус ремонта машины №${processDto.carId} изменен на \'Взято в ремонт\' " +
                    "<a href=\"http://localhost:8080/api/rest/repair-service/take-to-repair-process?repairProcessId=${processDto.id}\">:JGF</a>")
            true
        } else
            false
    }

    override fun updateRepairProcess(repairProcessId: Long, repairInfoDto: RepairInfoDto, repairRequestList: MutableList<Long>?): Boolean {
        val repairProcess = storageFeignClient.getRepairProcessById(repairProcessId)
        if (!CollectionUtils.isEmpty(repairRequestList)) repairRequestList?.stream()?.forEach { repairProcess.repairRequestIds?.add(it) }
        if (repairProcess.repairProcessState!! != RepairProcessState.NEED_LONG_REPAIR) {
            repairProcess.carRepairStatesIds!!.add(carRepairStateService.changeRepairState(repairInfoDto))
            repairProcess.repairProcessState = RepairProcessState.entries[repairInfoDto.repairProcessStateNumber]
            return if (storageFeignClient.saveRepairProcess(repairProcess) > 0) {
                LOGGER.info("Успешно обновили repairProcess"); true }
            else { LOGGER.info("При сохранение repairProcess произошла ошибка!"); false }
        } else {
            LOGGER.info("Для этого ремонтного процесса был создан длительный ремонт. Обновляем его...")
            val longRepairProcessDto = storageFeignClient.getLongRepairProcessByRepairId(repairProcessId)
            longRepairProcessDto.updatedAt = LocalDateTime.now()
            longRepairProcessDto.carLongRepairStateIds.add(carLongRepairStateService.updateLongRepairState(repairInfoDto))
            val result = storageFeignClient.saveLongRepairProcess(longRepairProcessDto)
            return if (result != null) { LOGGER.info("Успешно обновили LongRepairProcess с ID: $repairProcessId"); true }
            else { LOGGER.info("При обновлении LongRepairProcess с ID: $repairProcessId произошла ошибка"); false }
           }
    }

    override fun closeRepairProcess(repairProcessId: Long, repairInfoDto: RepairInfoDto): Boolean {

        val repairProcess = storageFeignClient.getRepairProcessById(repairProcessId)
        if (repairProcess.repairProcessState!!.ordinal == RepairProcessState.DONE.ordinal && !repairProcess.actual!!) {
            throw Exception("Процесс уже завершен. Повторное завершение не возможно")
        }

        repairProcess.actualCompletionTime = LocalDateTime.now()
        repairProcess.differenceWorkTime = ChronoUnit.MINUTES.between(LocalDateTime.now(), repairProcess.repairStartAt)

        if (repairProcess.repairProcessState!!.ordinal != RepairProcessState.NEED_LONG_REPAIR.ordinal) {
            repairProcess.carRepairStatesIds!!.add(carRepairStateService.createEndRepairState(repairProcessId, repairInfoDto))
        } else {
            LOGGER.info("Для этого ремонтного процесса был создан длительный ремонт. Завершаем его...")
            val longRepairProcessDto = storageFeignClient.getLongRepairProcessByRepairId(repairProcessId)

            longRepairProcessDto.updatedAt = LocalDateTime.now()
            longRepairProcessDto.timeContainsInMinutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), longRepairProcessDto.createdAt)
            longRepairProcessDto.carLongRepairStateIds.add(carLongRepairStateService.createEndLongRepairState(repairInfoDto))
            val longProcessResult = storageFeignClient.saveLongRepairProcess(longRepairProcessDto)
            if (longProcessResult != null) { LOGGER.info("Успешно завершили LongRepairProcess с ID: $repairProcessId") }
            else { LOGGER.info("При завершении LongRepairProcess с ID: $repairProcessId произошла ошибка"); return false}
        }
        repairProcess.repairProcessState = RepairProcessState.DONE
        repairProcess.actual = false
        val result = storageFeignClient.saveRepairProcess(repairProcess)
        if (result > 0) {
            val mapParts: MutableMap<UUID, String> = mutableMapOf()
            repairPartsServiceFeignClient.getInstalledRepairParts(result).forEach { mapParts[it.number] = it.name!! }
            telegramService.sendMessage("Ремонт машины №${repairInfoDto.carNumber} Успешно завершен! \n " +
                    "Были установлены запчасти: ${mapParts.entries}")
            return true
        }
        return false
        }
}


























