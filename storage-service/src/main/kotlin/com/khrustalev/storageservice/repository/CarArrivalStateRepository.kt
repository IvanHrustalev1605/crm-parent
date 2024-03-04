package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.CarArrivalState
import org.springframework.data.jpa.repository.JpaRepository

interface CarArrivalStateRepository : JpaRepository<CarArrivalState, Long> {
    fun findByCar_NumberOrderByArrivalTimeDesc(carNumber: String) : CarArrivalState
}