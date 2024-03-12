package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.CarDto
import com.khrustalev.storageservice.entity.enums.CarClassification
import com.khrustalev.storageservice.entity.schems.storage.Car
import com.khrustalev.storageservice.service.abstracts.DriverService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class CarMapper(@Lazy private val driverService: DriverService) {
    fun toDto(car: Car) : CarDto = CarDto(
        id = car.id,
        mileage = car.mileage,
        model = car.model,
        number = car.number,
        vinNumber = car.vinNumber,
        carClassification = car.carClassification?.ordinal,
        driverId = car.driver?.id
    )
    fun toEntity(carDto: CarDto) : Car = Car().also {
        it.id = carDto.id
        it.carClassification = CarClassification.entries[carDto.carClassification!!]
        it.mileage = carDto.mileage
        it.model = carDto.model
        it.number = carDto.number
        it.vinNumber = carDto.vinNumber
        it.driver = if (carDto.driverId != null) driverService.findById(carDto.driverId) else null
    }
}
