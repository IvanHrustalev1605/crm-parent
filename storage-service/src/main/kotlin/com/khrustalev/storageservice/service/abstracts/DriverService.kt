package com.khrustalev.storageservice.service.abstracts

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id
import com.khrustalev.storageservice.dto.DriverDto
import com.khrustalev.storageservice.entity.Driver

interface DriverService {
    fun findById(id : Long?) : Driver
    fun findByCarId(carId: Long): DriverDto?
}