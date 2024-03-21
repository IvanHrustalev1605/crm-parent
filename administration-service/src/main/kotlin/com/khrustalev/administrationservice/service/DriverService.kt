package com.khrustalev.administrationservice.service

import com.khrustalev.administrationservice.dto.CarArrivalStateDto
import com.khrustalev.administrationservice.dto.CarDto
import com.khrustalev.administrationservice.dto.DriverDto
import com.khrustalev.administrationservice.dto.RepairDto

interface DriverService {
    fun getAllDrivers() : MutableList<DriverDto>
    fun getDriversById(id: Long) : DriverDto
    fun getDriversByFIO(fio: String) : MutableList<DriverDto>
    fun saveDrivers(driverDto: DriverDto) : DriverDto
    fun getCarByDriverId(driverId: Long) : CarDto
    fun getAllRepairs(driverId: Long) : MutableList<RepairDto>
    fun getAllArrivals(driverId: Long) : MutableList<CarArrivalStateDto>
    fun getDriversByLicense(license: String): DriverDto
}