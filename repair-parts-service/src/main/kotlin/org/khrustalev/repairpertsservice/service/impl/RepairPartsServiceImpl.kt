package org.khrustalev.repairpertsservice.service.impl

import org.khrustalev.repairpertsservice.dto.RepairPartsDto
import org.khrustalev.repairpertsservice.feign.RepairPartsFeignClient
import org.khrustalev.repairpertsservice.service.RepairPartsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import java.time.LocalDateTime
import java.util.UUID

@Service
class RepairPartsServiceImpl(private val repairPartsFeignClient: RepairPartsFeignClient) : RepairPartsService {
    private val logger: Logger = LoggerFactory.getLogger(RepairPartsServiceImpl::class.java)

    override fun installPartByNumber(partNumberList: List<UUID>, carId: Long): Boolean {
        val partsList = repairPartsFeignClient.getByNumberList(partNumberList.toMutableList())
        if (!CollectionUtils.isEmpty(partsList)) {
            partsList.forEach {
                it.installed = true
                it.installedAt = LocalDateTime.now()
                it.carId = carId
                repairPartsFeignClient.save(it)
            }
            return true
        }
        logger.info("No parts found by NUMBER: $partNumberList")
        return false
    }

    override fun updatePart(repairPartsDto: RepairPartsDto): Boolean {
        return repairPartsFeignClient.save(repairPartsDto) != null
    }

    override fun acceptRepairParts(repairPartsList: MutableList<RepairPartsDto>): Boolean {
        TODO("Not yet implemented")
    }

}



















