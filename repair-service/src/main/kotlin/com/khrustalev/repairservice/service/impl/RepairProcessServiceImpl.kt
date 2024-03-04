package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.RepairProcessDto
import com.khrustalev.repairservice.dto.enums.RepairState
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.CarRepairStateService
import com.khrustalev.repairservice.service.RepairProcessService
import com.khrustalev.repairservice.service.RepairRequestService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RepairProcessServiceImpl(private val storageFeignClient: StorageFeignClient) : RepairProcessService {
    private val LOGGER: Logger = LoggerFactory.getLogger(RepairProcessServiceImpl::class.java)

    override fun createRepairProcess(
        carNumber: String,
        repairInfoDto: RepairInfoDto,
        repairRequestList: MutableList<Long>
    ): RepairProcessDto? {
            val repairProcessDto = RepairProcessDto()
            repairProcessDto.carId = storageFeignClient.findCarByCarNumber(carNumber)
            repairProcessDto.createTime = LocalDateTime.now()
            repairProcessDto.endRepair = LocalDateTime.now().plusHours(24)
            repairProcessDto.actual = true
            repairProcessDto.repairState = RepairState.entries[repairInfoDto.repairStateNumber]
            repairProcessDto.carArrivalTime = LocalDateTime.now().minusMinutes(25)
            repairProcessDto.carRepairStatesIds = repairInfoDto.repairStateIds?.toMutableList()
            repairProcessDto.repairRequestIds = repairRequestList
            return if (storageFeignClient.saveRepairProcess(repairProcessDto)) {
                LOGGER.info("Успешно сохранили repairProcess")
                repairProcessDto
            } else {
                LOGGER.info("При сохранение repairProcess произошла ошибка!")
                null
            }
        }
}