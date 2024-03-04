package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.Repair
import org.springframework.data.jpa.repository.JpaRepository

interface RepairRepository : JpaRepository<Repair, Long> {
    fun findByCar_NumberAndActualIsTrue(carNumber: String) : Repair?
}