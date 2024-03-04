package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.RepairRequestDto
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.RepairRequestService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RepairRequestServiceImpl(private val storageFeignClient: StorageFeignClient) : RepairRequestService {
    private val LOGGER: Logger = LoggerFactory.getLogger(RepairRequestServiceImpl::class.java)

    override fun createRepairRequest(
        repairDescription: String,
        engineerId: Long,
        carNumber: String,
        repairProcessId: Long?,
        requestNumber: Long
    ): RepairRequestDto {
        val repairRequestDto = RepairRequestDto().also {
            it.carId = storageFeignClient.findCarByCarNumber(carNumber)
            it.repairId = repairProcessId
            it.requestDescription = repairDescription
            it.engineerId = engineerId
            it.createDate = LocalDateTime.now()
            it.requestNumber = requestNumber
        }
        LOGGER.info("Сохраняем заявку на ремонт $repairRequestDto")
        storageFeignClient.saveRepairRequest(repairRequestDto)
        return repairRequestDto
    }

    override fun getAllRepairRequestsByCarNumber(carNumber: String): MutableList<RepairRequestDto>? {
        return storageFeignClient.getRepairRequestsByCarNumber(carNumber)
    }

    override fun getActualRepairRequest(actualDate: LocalDateTime, carNumber: String): MutableList<Long> {
        return storageFeignClient.findRepairRequestForActualRepairProcess(actualDate, carNumber)
    }
}