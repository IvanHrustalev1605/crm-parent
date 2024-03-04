package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.RepairDto
import com.khrustalev.storageservice.entity.Repair
import com.khrustalev.storageservice.exception.NotFoundEntityException
import com.khrustalev.storageservice.mappers.RepairMapper
import com.khrustalev.storageservice.repository.RepairRepository
import com.khrustalev.storageservice.service.abstracts.RepairService
import org.springframework.stereotype.Service

@Service("repair-service")
class RepairServiceImpl(private val repairRepository: RepairRepository,
                        private val repairMapper: RepairMapper
) : RepairService {
    override fun findRepairById(id: Long): Repair? {
        return repairRepository.findById(id).orElseThrow { NotFoundEntityException("Repair by id $id not found") }
    }

    override fun findRepairByCarNumberAndActualTrue(carNumber: String): RepairDto? {
        return repairMapper.toDto(repairRepository.findByCar_NumberAndActualIsTrue(carNumber)!!)
    }

    override fun save(repairDto: RepairDto): Boolean {
        val toEntity = repairMapper.toEntity(repairDto)
        repairRepository.save(toEntity)
        return true
    }
}