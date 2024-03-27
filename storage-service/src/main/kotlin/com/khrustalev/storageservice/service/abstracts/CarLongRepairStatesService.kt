package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.CarLongRepairStateDto
import com.khrustalev.storageservice.entity.schems.storage.CarLongRepairState

interface CarLongRepairStatesService {
    fun toDto(carLongRepairState: CarLongRepairState) : CarLongRepairStateDto
    fun save(carLongRepairStateDto: CarLongRepairStateDto) : CarLongRepairStateDto
    fun findById(id: Long) : CarLongRepairState
    fun getPreviousStateByCarId(carId: Long): CarLongRepairStateDto?
}