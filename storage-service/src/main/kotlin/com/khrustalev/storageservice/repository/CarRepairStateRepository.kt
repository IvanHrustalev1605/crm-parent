package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.CarRepairState
import org.springframework.data.jpa.repository.JpaRepository

interface CarRepairStateRepository : JpaRepository<CarRepairState, Long> {
}