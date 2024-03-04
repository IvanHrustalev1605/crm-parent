package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.Repair
import com.khrustalev.storageservice.exception.NotFoundEntityException
import com.khrustalev.storageservice.repository.RepairRepository
import com.khrustalev.storageservice.service.abstracts.RepairService
import org.springframework.stereotype.Service

@Service
class RepairServiceImpl(private val repairRepository: RepairRepository) : RepairService {
    override fun findRepairById(id: Long): Repair? {
        return repairRepository.findById(id).orElseThrow { NotFoundEntityException("Repair by id $id not found") }
    }
}