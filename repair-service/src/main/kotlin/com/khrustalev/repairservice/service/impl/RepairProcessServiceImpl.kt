package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.RepairProcessDto
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.CarRepairStateService
import com.khrustalev.repairservice.service.RepairProcessService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RepairProcessServiceImpl(private val storageFeignClient: StorageFeignClient,
                               private val carRepairStateService: CarRepairStateService) : RepairProcessService {
    private val LOGGER: Logger = LoggerFactory.getLogger(RepairProcessServiceImpl::class.java)



    override fun createRepairProcess(carNumber: String, repairInfoDto: RepairInfoDto): RepairProcessDto? {
        val repairProcess = storageFeignClient.getRepairProcessByCarNumber(carNumber)
        if (repairProcess != null) {
            val carRepairState = carRepairStateService.createChangeRepairState(repairInfoDto)
            repairProcess.carRepairStateDtoList?.add(carRepairState)
            repairProcess.carId = repairInfoDto.carId
            repairProcess.repairRequestId =
            if (storageFeignClient.saveRepairProcess(repairProcess)) {
                LOGGER.info("Успешно сохранили repairProcess")
                return repairProcess
            } else {
                LOGGER.info("При сохранение repairProcess произошла ошибка!")
                return null
            }

        } else {
            return repairProcess
        }
    }

}