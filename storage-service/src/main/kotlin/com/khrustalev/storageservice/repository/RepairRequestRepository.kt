package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.RepairRequest
import org.springframework.data.jpa.repository.JpaRepository

interface RepairRequestRepository : JpaRepository<RepairRequest, Long> {
    fun findAllByCar_Number(carNumber: String) : MutableList<RepairRequest>
}