package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.Driver
import com.khrustalev.storageservice.repository.DriverRepository
import com.khrustalev.storageservice.service.abstracts.DriverService
import org.springframework.stereotype.Service

@Service
class DriverServiceImpl(private val driverRepository: DriverRepository) : DriverService {
    override fun findById(id: Long?): Driver = driverRepository.findById(id!!).orElse(null)
}