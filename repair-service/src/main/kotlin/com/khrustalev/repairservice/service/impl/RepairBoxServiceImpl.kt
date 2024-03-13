package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.RepairBoxDto
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.RepairBoxService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RepairBoxServiceImpl(private val storageFeignClient: StorageFeignClient) : RepairBoxService {
    private val LOGGER: Logger = LoggerFactory.getLogger(RepairBoxServiceImpl::class.java)
    override fun getFreeBoxes(): MutableList<RepairBoxDto> {
        return storageFeignClient.getFreeBox().body!!
    }

    override fun busyBox(boxNumber: Int): Boolean {
        val responseEntity = storageFeignClient.getBoxByNumber(boxNumber)
        return if (responseEntity.statusCode.is2xxSuccessful) {
            val boxDto = responseEntity.body!!
            if (!boxDto.isFree) {
                LOGGER.info("Бокс занят. Нужно подождать")
                return false
            }
            boxDto.isFree = false
            storageFeignClient.saveBox(boxDto)
            true
        } else false
    }

    override fun setBoxFree(boxNumber: Int): Boolean {
        val responseEntity = storageFeignClient.getBoxByNumber(boxNumber)
        return if (responseEntity.statusCode.is2xxSuccessful) {
            val boxDto = responseEntity.body!!
            boxDto.isFree = true
            storageFeignClient.saveBox(boxDto)
            true
        } else false
    }

    override fun getBoxByNumber(boxNumber: Int): RepairBoxDto {
        return storageFeignClient.getBoxByNumber(boxNumber).body!!
    }
}
