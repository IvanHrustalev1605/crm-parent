package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.LongRepairProcessDto
import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.enums.RepairProcessState
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.CarLongRepairStateService
import com.khrustalev.repairservice.service.LongRepairService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class LongRepairServiceImpl(private val storageFeignClient: StorageFeignClient,
                            private val carLongRepairStateService: CarLongRepairStateService) : LongRepairService {

    private val logger: Logger = LoggerFactory.getLogger(LongRepairServiceImpl::class.java)

    override fun createNewLongRepairProcess(repairInfoDto: RepairInfoDto, repairId: Long): LongRepairProcessDto? {
        val repairProcessDto = storageFeignClient.getRepairProcessById(repairId)
        // TODO: тут можно додумать что еще изменЯть в обычном процессе ремонта, в случае перехода его в длительный
        if (repairProcessDto.repairProcessState!!.ordinal != 4) {
            repairProcessDto.repairProcessState = RepairProcessState.NEED_LONG_REPAIR
            storageFeignClient.saveRepairProcess(repairProcessDto)
            logger.info("Ремонтный процесс с ID: $repairId успешно изменен на длительный! ")
        }
        val longRepairProcessDto = LongRepairProcessDto()
        longRepairProcessDto.expectedEnd = repairInfoDto.expectedEnd
        longRepairProcessDto.timeContainsInMinutes = ChronoUnit.MINUTES.between(repairInfoDto.expectedEnd, LocalDateTime.now())
        longRepairProcessDto.createdAt = LocalDateTime.now()
        longRepairProcessDto.updatedAt = LocalDateTime.now()
        longRepairProcessDto.repairId = repairId
        longRepairProcessDto.descriptionProblems = repairInfoDto.repairProblems
        longRepairProcessDto.carLongRepairStateIds
            .add(carLongRepairStateService.createNewLongRepairState(repairInfoDto, repairProcessDto).takeIf { it > 0 }
                ?: throw Exception("При создании начальной CarLongRepairState произошла ошибка. Продолжение не возможно!"))
        longRepairProcessDto.reasons = repairInfoDto.reasons
        return storageFeignClient.saveLongRepairProcess(longRepairProcessDto)
            ?: throw Exception("При сохранение процесса длительной ремонта произошла ошибка!")

    }

    override fun endLongRepairProcess(repairInfoDto: RepairInfoDto): LongRepairProcessDto? {
        return null
    }

    override fun updateLongRepairProcess(repairInfoDto: RepairInfoDto): LongRepairProcessDto? {
        val actualLongRepairProcess = storageFeignClient.getActualLongRepairProcessByCarId(repairInfoDto.carId!!)
        actualLongRepairProcess.updatedAt = LocalDateTime.now()
        actualLongRepairProcess.carLongRepairStateIds.add(carLongRepairStateService.updateLongRepairState(repairInfoDto).takeIf { it > 0 }
            ?: throw Exception("При добавлении CarLongRepairState произошла ошибка. Продолжение не возможно!"))
        return storageFeignClient.saveLongRepairProcess(actualLongRepairProcess)
            ?: throw Exception("При сохранение процесса длительной ремонта произошла ошибка!")
    }
}