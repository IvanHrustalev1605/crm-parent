package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.LongRepairProcessDto
import com.khrustalev.storageservice.mappers.LongRepairProcessMapper
import com.khrustalev.storageservice.repository.LongRepairProcessRepository
import com.khrustalev.storageservice.service.abstracts.LongRepairProcessService
import jakarta.persistence.EntityNotFoundException
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class LongRepairProcessServiceImpl(private val longRepairProcessRepository: LongRepairProcessRepository,
                                   @Lazy private val longRepairProcessMapper: LongRepairProcessMapper) : LongRepairProcessService {
    override fun save(longRepairProcessDto: LongRepairProcessDto): LongRepairProcessDto? {
        return longRepairProcessMapper.toDto(longRepairProcessRepository.save(longRepairProcessMapper.toEntity(longRepairProcessDto)))
    }

    override fun getAll(): MutableList<LongRepairProcessDto> {
        return longRepairProcessRepository.findAll().map { longRepairProcessMapper.toDto(it) }.toMutableList()
    }

    override fun findActualByCarId(carId: Long): LongRepairProcessDto? {
        return longRepairProcessMapper.toDto(longRepairProcessRepository.findActualByCarId(carId))
    }

    override fun findByRepairId(repairProcessId: Long): LongRepairProcessDto? {
        return longRepairProcessRepository.findByRepairId(repairProcessId)
            ?.let { longRepairProcessMapper.toDto(it) }
            ?: throw EntityNotFoundException("No LongRepairProcess found by $repairProcessId")
    }
}