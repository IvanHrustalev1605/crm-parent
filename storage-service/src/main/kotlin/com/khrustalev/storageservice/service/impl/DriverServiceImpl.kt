package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.CarArrivalStateDto
import com.khrustalev.storageservice.dto.CarDto
import com.khrustalev.storageservice.dto.DriverDto
import com.khrustalev.storageservice.dto.RepairDto
import com.khrustalev.storageservice.entity.schems.storage.Driver
import com.khrustalev.storageservice.exception.NotFoundEntityException
import com.khrustalev.storageservice.mappers.CarArrivalStateMapper
import com.khrustalev.storageservice.mappers.CarMapper
import com.khrustalev.storageservice.mappers.DriverMapper
import com.khrustalev.storageservice.mappers.RepairMapper
import com.khrustalev.storageservice.repository.DriverRepository
import com.khrustalev.storageservice.service.abstracts.DriverService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class DriverServiceImpl(private val driverRepository: DriverRepository,
                        private val driverMapper: DriverMapper,
                        @Lazy private val carMapper: CarMapper,
                        @Lazy private val repairMapper: RepairMapper,
                        @Lazy private val carArrivalStateMapper: CarArrivalStateMapper) : DriverService {

    override fun findById(id: Long?): Driver = driverRepository.findById(id!!).orElse(null)
    override fun findByCarId(carId: Long): DriverDto? = driverRepository.findByCar_Id(carId)?.let { driverMapper.toDto(it) }

    override fun getAllDrivers(): MutableList<DriverDto> {
        return driverRepository.findAll().map { driverMapper.toDto(it) }.toMutableList()
    }

    override fun getDriversById(id: Long): DriverDto {
        return driverRepository.findById(id).map { driverMapper.toDto(it) }.orElseThrow { NotFoundEntityException("Driver by ID: $id not found!") }
    }

    override fun getDriversByFIO(fio: String): MutableList<DriverDto> {
        return driverRepository.getDriversByFio(fio)
            .map { driverMapper.toDto(it) }
            .toMutableList()
    }

    override fun saveDrivers(driverDto: DriverDto): DriverDto {
        return driverMapper.toDto(driverRepository.save(driverMapper.toEntity(driverDto)))
    }

    override fun getCarByDriverId(driverId: Long): CarDto {
        return carMapper.toDto(driverRepository.getDriversCar(driverId))
    }

    override fun getAllRepairs(driverId: Long): MutableList<RepairDto> {
        return driverRepository.getRepairProcessByDriverId(driverId)
            .map { repairMapper.toDto(it) }
            .toMutableList()
    }

    override fun getAllArrivals(driverId: Long): MutableList<CarArrivalStateDto> {
        return driverRepository.getAllArrivalsByDriverId(driverId)
            .map { carArrivalStateMapper.toDto(it) }
            .toMutableList()
    }

    override fun getDriversByLicense(license: String): DriverDto {
        return driverMapper.toDto(driverRepository.findByLicense(license))
    }
}