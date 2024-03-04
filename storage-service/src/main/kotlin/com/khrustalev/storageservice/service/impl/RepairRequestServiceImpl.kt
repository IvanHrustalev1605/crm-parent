package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.RepairRequestDto
import com.khrustalev.storageservice.entity.RepairRequest
import com.khrustalev.storageservice.mappers.RepairRequestMapper
import com.khrustalev.storageservice.repository.RepairRequestRepository
import com.khrustalev.storageservice.service.abstracts.RepairRequestService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service("storage-repair-request")
class RepairRequestServiceImpl(private val repairRequestRepository: RepairRequestRepository,
                               @Lazy private val repairRequestMapper: RepairRequestMapper) : RepairRequestService {
    override fun createRepairRequest(repairRequestDto: RepairRequestDto): Boolean {
        repairRequestRepository.save(repairRequestMapper.toEntity(repairRequestDto))
        return true
    }

    override fun getAllRepairRequestByCarNumber(carNumber: String): MutableList<RepairRequestDto>? {
        return repairRequestRepository.findAllByCar_Number(carNumber).stream()
            .map { repairRequestMapper.toDto(it) }
            ?.toList()
            ?.toMutableList()
    }

    override fun getAllByIds(ids: MutableList<Long>): MutableList<RepairRequest> {
        return repairRequestRepository.findAllById(ids)
    }

    override fun getActualForRepairProcess(actualDate: LocalDateTime, carNumber: String): MutableList<Long> {
        return repairRequestRepository.getActual(actualDate, carNumber).stream().map { it.id!! }.toList().toMutableList()
    }
}