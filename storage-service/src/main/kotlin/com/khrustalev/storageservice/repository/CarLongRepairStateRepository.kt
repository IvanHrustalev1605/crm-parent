package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.storage.CarLongRepairState
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CarLongRepairStateRepository : JpaRepository<CarLongRepairState, Long> {

    @Query(value = "select clrs from CarLongRepairState clrs " +
            "where clrs.car.id =:carId " +
            "order by clrs.createdAt DESC " +
            "limit 1")
    fun findPreviousByCarId(@Param("carId") carId: Long) : CarLongRepairState?
}