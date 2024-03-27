package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.dto.LongRepairProcessDto
import com.khrustalev.storageservice.entity.schems.storage.LongRepairProcess
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface LongRepairProcessRepository : JpaRepository<LongRepairProcess, Long> {

    @Query(value = "select lrp from LongRepairProcess lrp where " +
            "lrp.repair.car.id =:carId and lrp.repair.actual = true")
    fun findActualByCarId(@Param("carId")carId: Long) : LongRepairProcess

    fun findByRepairId(repairId: Long) : LongRepairProcess?
}