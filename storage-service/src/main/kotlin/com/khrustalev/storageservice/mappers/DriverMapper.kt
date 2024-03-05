package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.DriverDto
import com.khrustalev.storageservice.entity.Driver
import com.khrustalev.storageservice.service.CarService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class DriverMapper(@Lazy private val carService: CarService) {
    fun toDto(driver: Driver) : DriverDto = DriverDto(
        id = driver.id,
        personInfo = driver.personInfo,
        license = driver.license,
        position = driver.position,
        timeToMakeRequestStart = driver.timeToMakeRequestEnd,
        timeToMakeRequestEnd = driver.timeToMakeRequestEnd,
        carId = driver.car?.id
    )
    fun toEntity(driverDto: DriverDto) : Driver = Driver().also {
        it.id = driverDto.id
        it.personInfo = driverDto.personInfo
        it.license = driverDto.license
        it.timeToMakeRequestEnd = driverDto.timeToMakeRequestEnd
        it.timeToMakeRequestStart = driverDto.timeToMakeRequestStart
        it.position = driverDto.position!!
        it.car = if (driverDto.carId != null) carService.findById(driverDto.carId) else null
    }
}
