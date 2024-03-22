package com.khrustalev.administrationservice.service.impl

import com.khrustalev.administrationservice.dto.AcceptablePartsDto
import com.khrustalev.administrationservice.dto.RepairPartsDto
import com.khrustalev.administrationservice.feign.RepairPartsServiceFeignClient
import com.khrustalev.administrationservice.service.RepairPartsServiceService
import org.springframework.stereotype.Service

@Service
class RepairPartsServiceServiceImpl(private val repairPartsServiceFeignClient: RepairPartsServiceFeignClient) : RepairPartsServiceService {
    override fun putPartsToStoragePlace(acceptablePartsDtoList: MutableList<AcceptablePartsDto>): Boolean {
            return repairPartsServiceFeignClient.putPartsToStoragePlace(acceptablePartsDtoList).statusCode.is2xxSuccessful
    }

    override fun takePartsFromStoragePlace(map: MutableMap<Long, Long>): Boolean {
        return repairPartsServiceFeignClient.takePartsFromStoragePlace(map)
    }

    override fun getInstalledPartsDuringRepair(repairId: Long): MutableList<RepairPartsDto> {
        return repairPartsServiceFeignClient.getInstalledRepairParts(repairId)
    }
}