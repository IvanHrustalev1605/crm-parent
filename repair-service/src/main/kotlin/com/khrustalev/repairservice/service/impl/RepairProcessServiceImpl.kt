package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.RepairProcessDto
import com.khrustalev.repairservice.dto.enums.RepairProcessState
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.CarArrivalStateService
import com.khrustalev.repairservice.service.CarRepairStateService
import com.khrustalev.repairservice.service.RepairProcessService
import com.khrustalev.repairservice.service.TelegramService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class RepairProcessServiceImpl(private val storageFeignClient: StorageFeignClient,
                               private val carRepairStateService: CarRepairStateService,
                               private val telegramService: TelegramService,
                               private val carArrivalStateService: CarArrivalStateService) : RepairProcessService {
    private val LOGGER: Logger = LoggerFactory.getLogger(RepairProcessServiceImpl::class.java)

    override fun createNewRepairProcess(
        repairInfoDto: RepairInfoDto,
        repairRequestList: MutableList<Long>
    ): RepairProcessDto? {
        val arrivalStateDto = carArrivalStateService.getActualArrivalStateByCarId(repairInfoDto.carId!!)
        if (arrivalStateDto?.repairRequestWritten == false) {
                throw Exception("Заявка на ремонт не написана или не согласована. Нельзя приступить к ремонту!")
            }
            val repairProcessDto = RepairProcessDto()
            repairProcessDto.carId = repairInfoDto.carId
            repairProcessDto.createTime = LocalDateTime.now()
            repairProcessDto.endRepair = LocalDateTime.now().plusHours(24)
            repairProcessDto.repairProcessState = RepairProcessState.NEW
            repairProcessDto.carRepairStatesIds = mutableListOf(carRepairStateService.createNewRepairState(repairInfoDto))
            repairProcessDto.repairRequestIds = repairRequestList
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

    override fun updateRepairProcess(
        repairProcessId: Long,
        repairInfoDto: RepairInfoDto,
        repairRequestList: MutableList<Long>?
    ): Boolean {

        val repairProcess = storageFeignClient.getRepairProcessById(repairProcessId)

        repairProcess.carRepairStatesIds!!.add(carRepairStateService.changeRepairState(repairInfoDto))
        if (!CollectionUtils.isEmpty(repairRequestList)) repairRequestList?.stream()?.forEach { repairProcess.repairRequestIds?.add(it) }
        repairProcess.repairProcessState = RepairProcessState.entries[repairInfoDto.repairProcessStateNumber]
        return if (storageFeignClient.saveRepairProcess(repairProcess) > 0) {
            LOGGER.info("Успешно обновили repairProcess")
            true
        } else {
            LOGGER.info("При сохранение repairProcess произошла ошибка!")
            false
        }
    }

    override fun closeRepairProcess(repairProcessId: Long, repairInfoDto: RepairInfoDto): Boolean {

        val repairProcess = storageFeignClient.getRepairProcessById(repairProcessId)
        repairProcess.actual = false
        repairProcess.actualCompletionTime = LocalDateTime.now()
        repairProcess.differenceWorkTime = ChronoUnit.MINUTES.between(LocalDateTime.now(), repairProcess.endRepair)
        repairProcess.carRepairStatesIds!!.add(carRepairStateService.createEndRepairState(repairProcessId, repairInfoDto))
        repairProcess.repairProcessState = RepairProcessState.DONE
        val result = storageFeignClient.saveRepairProcess(repairProcess)
        if (result > 0) {
            telegramService.sendMessage("Ремонт машины №${repairInfoDto.carNumber} Успешно завершен! \n " +
                    "Были установлены запчасти: ${storageFeignClient.getRepairPartsByRepairId(result).body}")
            return true
        }
        return false
    }

}


























