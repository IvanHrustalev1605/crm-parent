package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.CarArrivalState
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface CarArrivalStateRepository : JpaRepository<CarArrivalState, Long> {
    fun findFirstByCar_NumberOrderByArrivalTimeDesc(@Param("carNumber") carNumber: String) : CarArrivalState
}