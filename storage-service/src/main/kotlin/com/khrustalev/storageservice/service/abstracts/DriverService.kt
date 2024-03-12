package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.DriverDto
import com.khrustalev.storageservice.entity.schems.storage.Driver

interface DriverService {
    fun findById(id : Long?) : Driver
    fun findByCarId(carId: Long): DriverDto?
}