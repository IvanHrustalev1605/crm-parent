package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.RepairDto
import com.khrustalev.storageservice.exception.NotFoundEntityException
import com.khrustalev.storageservice.mappers.RepairMapper
import com.khrustalev.storageservice.repository.RepairRepository
import com.khrustalev.storageservice.service.abstracts.RepairService
import org.springframework.stereotype.Service

@Service("repair-service")
class RepairServiceImpl(private val repairRepository: RepairRepository,
                        private val repairMapper: RepairMapper
) : RepairService {

    override fun findRepairById(id: Long): RepairDto? {
        return repairMapper.toDto(repairRepository.findById(id).orElseThrow { NotFoundEntityException("Repair by id $id not found") })
    }

    override fun findRepairByCarNumberAndActualTrue(carNumber: String): RepairDto? {
        return repairMapper.toDto(repairRepository.findByCar_NumberAndActualIsTrue(carNumber)!!)
    }

    override fun save(repairDto: RepairDto): Long {
        return repairRepository.save(repairMapper.toEntity(repairDto)).id!!
    }

    override fun getAllRepairParts(repairId: Long): List<String> {
        val result =
            repairRepository.findById(repairId).get().carRepairState!!
                .stream()
                .map { it.repairParts!! }
                .toList()
        return result
    }
}