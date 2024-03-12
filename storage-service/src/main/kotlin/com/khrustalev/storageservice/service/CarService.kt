package com.khrustalev.storageservice.service

import com.khrustalev.storageservice.dto.CarDto
import com.khrustalev.storageservice.entity.schems.storage.Car

interface CarService {
    fun findById(carId: Long): Car?
    fun findCarIdByCarNumber(carNumber: String): Long
    fun findByVin(vin: String): CarDto
}
