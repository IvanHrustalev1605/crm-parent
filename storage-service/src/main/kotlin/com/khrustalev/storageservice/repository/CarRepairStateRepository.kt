package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.dto.CarRepairStateDto
import com.khrustalev.storageservice.entity.schems.storage.CarRepairState
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CarRepairStateRepository : JpaRepository<CarRepairState, Long> {
    @Query(value = "select crs from CarRepairState crs " +
            "where crs.car.id = :carId " +
            "order by crs.createdAt DESC " +
            "limit 1")
    fun findPreviousRepairState(@Param("carId") carId: Long) : CarRepairState
}