package com.khrustalev.storageservice.service

import com.khrustalev.storageservice.entity.Car

interface CarService {
    fun findById(carId: Long): Car?
    fun findCarIdByCarNumber(carNumber: String): Long
}
