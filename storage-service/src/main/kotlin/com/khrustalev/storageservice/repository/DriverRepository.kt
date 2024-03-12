package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.storage.Driver
import org.springframework.data.jpa.repository.JpaRepository

interface DriverRepository : JpaRepository<Driver, Long> {
    fun findByCar_Id(carId: Long) : Driver?
}