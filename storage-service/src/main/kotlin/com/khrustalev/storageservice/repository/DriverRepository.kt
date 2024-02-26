package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.Driver
import org.springframework.data.jpa.repository.JpaRepository

interface DriverRepository : JpaRepository<Driver, Long> {
}