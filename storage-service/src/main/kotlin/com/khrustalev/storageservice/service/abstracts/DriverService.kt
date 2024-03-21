package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.CarArrivalStateDto
import com.khrustalev.storageservice.dto.CarDto
import com.khrustalev.storageservice.dto.DriverDto
import com.khrustalev.storageservice.dto.RepairDto
import com.khrustalev.storageservice.entity.schems.storage.Driver

interface DriverService {
    fun findById(id : Long?) : Driver
    fun findByCarId(carId: Long): DriverDto?

    fun getAllDrivers() : MutableList<DriverDto>
    fun getDriversById(id: Long) : DriverDto
    fun getDriversByFIO(fio: String) : MutableList<DriverDto>
    fun saveDrivers(driverDto: DriverDto) : DriverDto
    fun getCarByDriverId(driverId: Long) : CarDto
    fun getAllRepairs(driverId: Long) : MutableList<RepairDto>
    fun getAllArrivals(driverId: Long) : MutableList<CarArrivalStateDto>
    fun getDriversByLicense(license: String): DriverDto
}