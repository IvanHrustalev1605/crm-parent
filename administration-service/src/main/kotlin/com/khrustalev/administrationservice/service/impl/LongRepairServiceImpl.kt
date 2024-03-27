package com.khrustalev.administrationservice.service.impl

import com.khrustalev.administrationservice.dto.LongRepairProcessDto
import com.khrustalev.administrationservice.dto.RepairInfoDto
import com.khrustalev.administrationservice.feign.RepairProcessFeignClient
import com.khrustalev.administrationservice.service.LongRepairService
import org.springframework.stereotype.Service

@Service
class LongRepairServiceImpl(private val repairProcessFeignClient: RepairProcessFeignClient) : LongRepairService {
    override fun create(repairInfoDto: RepairInfoDto, repairId: Long): LongRepairProcessDto? {
       return repairProcessFeignClient.createLongRepairRequest(repairInfoDto, repairId)
    }

    override fun update(repairInfoDto: RepairInfoDto): LongRepairProcessDto? {
        return repairProcessFeignClient.updateLongRepairRequest(repairInfoDto)
    }
}