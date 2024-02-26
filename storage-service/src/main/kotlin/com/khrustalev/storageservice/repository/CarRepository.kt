package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.Car
import org.springframework.data.jpa.repository.JpaRepository

interface CarRepository : JpaRepository<Car, Long> {
}