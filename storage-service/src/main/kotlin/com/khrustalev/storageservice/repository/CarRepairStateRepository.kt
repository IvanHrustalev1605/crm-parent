package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.storage.CarRepairState
import org.springframework.data.jpa.repository.JpaRepository

interface CarRepairStateRepository : JpaRepository<CarRepairState, Long> {
}