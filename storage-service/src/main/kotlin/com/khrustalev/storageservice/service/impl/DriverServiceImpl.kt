package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.DriverDto
import com.khrustalev.storageservice.entity.Driver
import com.khrustalev.storageservice.mappers.DriverMapper
import com.khrustalev.storageservice.repository.DriverRepository
import com.khrustalev.storageservice.service.abstracts.DriverService
import org.springframework.stereotype.Service

@Service
class DriverServiceImpl(private val driverRepository: DriverRepository, private val driverMapper: DriverMapper) : DriverService {
    override fun findById(id: Long?): Driver = driverRepository.findById(id!!).orElse(null)
    override fun findByCarId(carId: Long): DriverDto? = driverRepository.findByCar_Id(carId)?.let { driverMapper.toDto(it) }
}