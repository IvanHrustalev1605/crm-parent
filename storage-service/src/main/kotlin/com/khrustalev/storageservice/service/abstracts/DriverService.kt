package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.entity.Driver

interface DriverService {
    fun findById(id : Long?) : Driver
}