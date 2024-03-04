package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.RepairRequestDto
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.RepairRequestService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RepairRequestServiceImpl(private val storageFeignClient: StorageFeignClient) : RepairRequestService {
    private val LOGGER: Logger = LoggerFactory.getLogger(RepairRequestServiceImpl::class.java)

    override fun createRepairRequest(
        repairDescription: String,
        engineerId: Long,
        carNumber: String,
        repairProcessId: Long
    ): RepairRequestDto {
        val repairRequestDto = RepairRequestDto().also {
            it.carId = storageFeignClient.findCarByCarNumber(carNumber)
            it.repairId = repairProcessId
            it.requestDescription = repairDescription
            it.engineerId = engineerId
        }
        storageFeignClient.saveRepairRequest(repairRequestDto)
        return repairRequestDto
    }

    override fun getAllRepairRequestsByCarNumber(carNumber: String): MutableList<RepairRequestDto>? {
        return storageFeignClient.getRepairRequestsByCarNumber(carNumber)
    }
}