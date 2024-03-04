package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.RepairRequestDto
import com.khrustalev.storageservice.mappers.RepairRequestMapper
import com.khrustalev.storageservice.repository.RepairRequestRepository
import com.khrustalev.storageservice.service.abstracts.RepairRequestService
import org.springframework.stereotype.Service

@Service
class RepairRequestServiceImpl(private val repairRequestRepository: RepairRequestRepository,
                               private val repairRequestMapper: RepairRequestMapper) : RepairRequestService {
    override fun createRepairRequest(repairRequestDto: RepairRequestDto): Boolean {
        repairRequestRepository.save(repairRequestMapper.toEntity(repairRequestDto))
        return true
    }

    override fun getAllRepairRequestByCarNumber(carNumber: String): MutableList<RepairRequestDto>? {
        return repairRequestRepository.findAllByCar_Number(carNumber)?.stream()
            ?.map { repairRequestMapper.toDto(it) }
            ?.toList()
            ?.toMutableList()
    }
}