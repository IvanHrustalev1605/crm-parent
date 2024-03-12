package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.CarDto
import com.khrustalev.storageservice.entity.schems.storage.Car
import com.khrustalev.storageservice.exception.NotFoundEntityException
import com.khrustalev.storageservice.mappers.CarMapper
import com.khrustalev.storageservice.repository.CarRepository
import com.khrustalev.storageservice.service.CarService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CarServiceImpl(private val carRepository: CarRepository, private val carMapper: CarMapper) : CarService {
    override fun findById(carId: Long): Car? {
        return carRepository.findById(carId).orElseThrow { NotFoundEntityException("Car not found by id $carId") }
    }

    override fun findCarIdByCarNumber(carNumber: String): Long {
        return carRepository.customFindCarByNumber(carNumber)
    }

    override fun findByVin(vin: String): CarDto {
        return carMapper.toDto(carRepository.findByVinNumber(vin).orElseThrow { NotFoundEntityException("Car by VIN: $vin not found!") })
    }
}