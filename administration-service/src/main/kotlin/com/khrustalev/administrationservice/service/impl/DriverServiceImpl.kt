package com.khrustalev.administrationservice.service.impl

import com.khrustalev.administrationservice.dto.CarArrivalStateDto
import com.khrustalev.administrationservice.dto.CarDto
import com.khrustalev.administrationservice.dto.DriverDto
import com.khrustalev.administrationservice.dto.RepairDto
import com.khrustalev.administrationservice.feign.StorageFeignClient
import com.khrustalev.administrationservice.service.DriverService
import org.springframework.stereotype.Service

@Service
class DriverServiceImpl(private val storageFeignClient: StorageFeignClient) : DriverService {
    override fun getAllDrivers(): MutableList<DriverDto> {
        return storageFeignClient.getAllDrivers()
    }

    override fun getDriversById(id: Long): DriverDto {
        return storageFeignClient.getDriversById(id)
    }

    override fun getDriversByFIO(fio: String): MutableList<DriverDto> {
        return storageFeignClient.getDriversByFIO(fio)
    }

    override fun saveDrivers(driverDto: DriverDto): DriverDto {
        return storageFeignClient.saveDrivers(driverDto)
    }

    override fun getCarByDriverId(driverId: Long): CarDto {
        return storageFeignClient.getCarByDriverId(driverId)
    }

    override fun getDriversByLicense(license: String): DriverDto {
        return storageFeignClient.getDriversByLicense(license)
    }

    override fun getAllRepairs(driverId: Long): MutableList<RepairDto> {
        return storageFeignClient.getAllRepairs(driverId)
    }

    override fun getAllArrivals(driverId: Long): MutableList<CarArrivalStateDto> {
        return storageFeignClient.getAllArrivals(driverId)
    }
}