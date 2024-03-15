package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.CarDto
import com.khrustalev.storageservice.entity.schems.storage.Car
import com.khrustalev.storageservice.service.abstracts.CarClassificationService
import com.khrustalev.storageservice.service.abstracts.DriverService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class CarMapper(@Lazy private val driverService: DriverService,
                @Lazy private val carClassificationService: CarClassificationService
) {
    fun toDto(car: Car) : CarDto = CarDto(
        id = car.id,
        mileage = car.mileage,
        model = car.model,
        number = car.number,
        vinNumber = car.vinNumber,
        carClassificationId = car.carClassification?.id,
        driverId = car.driver?.id
    )
    fun toEntity(carDto: CarDto) : Car = Car().also {
        it.id = carDto.id
        it.carClassification = if (carDto.carClassificationId != null) carClassificationService.getClassificationById(carDto.carClassificationId) else null
        it.mileage = carDto.mileage
        it.model = carDto.model
        it.number = carDto.number
        it.vinNumber = carDto.vinNumber
        it.driver = if (carDto.driverId != null) driverService.findById(carDto.driverId) else null
    }
}
