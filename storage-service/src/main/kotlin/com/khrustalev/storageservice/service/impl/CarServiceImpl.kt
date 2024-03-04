package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.Car
import com.khrustalev.storageservice.exception.NotFoundEntityException
import com.khrustalev.storageservice.repository.CarRepository
import com.khrustalev.storageservice.service.CarService
import org.springframework.stereotype.Service

@Service
class CarServiceImpl(private val carRepository: CarRepository) : CarService {
    override fun findById(carId: Long): Car? {
        return carRepository.findById(carId).orElseThrow { NotFoundEntityException("Car not found by id $carId") }
    }

    override fun findCarIdByCarNumber(carNumber: String): Long {
        return carRepository.customFindCarByNumber(carNumber)
    }
}