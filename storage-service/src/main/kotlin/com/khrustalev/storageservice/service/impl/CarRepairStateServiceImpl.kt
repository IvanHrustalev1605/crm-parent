package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.schems.storage.CarRepairState
import com.khrustalev.storageservice.exception.NotFoundEntityException
import com.khrustalev.storageservice.repository.CarRepairStateRepository
import com.khrustalev.storageservice.service.abstracts.CarRepairStateService
import org.springframework.stereotype.Service

@Service
class CarRepairStateServiceImpl(private val carRepairStateRepository: CarRepairStateRepository) : CarRepairStateService {
    override fun findById(id: Long): CarRepairState {
        return carRepairStateRepository.findById(id)
            .orElseThrow { NotFoundEntityException("CarRepairState by id $id not found") }
    }

    override fun getDtoById(ids: MutableList<Long>): MutableList<CarRepairState>? {
        return ids.stream()
            .map { findById(it) }
            .toList()
            .toMutableList()
    }
}