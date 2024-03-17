package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.schems.dictionary.RepairPartsLargeGroup
import com.khrustalev.storageservice.repository.RepairPartsLargeGroupRepository
import com.khrustalev.storageservice.service.abstracts.RepairPartsLargeGroupService
import org.springframework.stereotype.Service

@Service
class RepairPartsLargeGroupServiceImpl(private val repairPartsLargeGroupRepository: RepairPartsLargeGroupRepository) : RepairPartsLargeGroupService {
    override fun findById(repairPartsLargeGroupId: Long): RepairPartsLargeGroup? {
        return repairPartsLargeGroupRepository.findById(repairPartsLargeGroupId).orElse(null)
    }
}