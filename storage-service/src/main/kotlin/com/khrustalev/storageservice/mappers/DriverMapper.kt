package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.DriverDto
import com.khrustalev.storageservice.entity.schems.storage.Driver
import com.khrustalev.storageservice.service.abstracts.CarService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class DriverMapper(@Lazy private val carService: CarService) {
    fun toDto(driver: Driver) : DriverDto = DriverDto(
        id = driver.id,
        personInfo = driver.personInfo,
        license = driver.license,
        position = driver.position,
        carId = driver.car?.id
    )
    fun toEntity(driverDto: DriverDto) : Driver = Driver().also {
        it.id = driverDto.id
        it.personInfo = driverDto.personInfo
        it.license = driverDto.license
        it.position = driverDto.position!!
        it.car = if (driverDto.carId != null) carService.findById(driverDto.carId) else null
    }
}
