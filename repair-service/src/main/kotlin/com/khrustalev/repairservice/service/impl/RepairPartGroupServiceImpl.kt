package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.RepairPartsDto
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.RepairPartGroupService
import org.springframework.stereotype.Service

@Service
class RepairPartGroupServiceImpl(private val storageFeignClient: StorageFeignClient) : RepairPartGroupService {
    override fun writeOffRepairPart(repairPartsDto: RepairPartsDto): Boolean {
        val repairPartsGroupDto = storageFeignClient.getRepairPartsGroupById(repairPartsDto.repairPartGroupId!!)
        repairPartsGroupDto.stockBalance = repairPartsGroupDto.stockBalance!!.minus(1)
        storageFeignClient.saveRepairPartGroup(repairPartsGroupDto)
        return true
    }
}