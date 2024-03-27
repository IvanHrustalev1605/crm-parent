package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.CarLongRepairStateDto
import com.khrustalev.storageservice.entity.schems.storage.CarLongRepairState
import com.khrustalev.storageservice.mappers.CarLongRepairStateMapper
import com.khrustalev.storageservice.repository.CarLongRepairStateRepository
import com.khrustalev.storageservice.service.abstracts.CarLongRepairStatesService
import jakarta.persistence.EntityNotFoundException
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class CarLongRepairStatesServiceImpl(private val carLongRepairStateRepository: CarLongRepairStateRepository,
                                     @Lazy private val carLongRepairStateMapper: CarLongRepairStateMapper) : CarLongRepairStatesService {
    override fun toDto(carLongRepairState: CarLongRepairState): CarLongRepairStateDto {
        return carLongRepairStateMapper.toDto(carLongRepairState)
    }

    override fun save(carLongRepairStateDto: CarLongRepairStateDto): CarLongRepairStateDto {
        return carLongRepairStateMapper.toDto(carLongRepairStateRepository.save(carLongRepairStateMapper.toEntity(carLongRepairStateDto)))
    }

    override fun getPreviousStateByCarId(carId: Long): CarLongRepairStateDto? {
        return carLongRepairStateRepository.findPreviousByCarId(carId)?.let { carLongRepairStateMapper.toDto(it) }
    }

    override fun findById(id: Long): CarLongRepairState {
        return carLongRepairStateRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No state found by id: $id") }
    }
}