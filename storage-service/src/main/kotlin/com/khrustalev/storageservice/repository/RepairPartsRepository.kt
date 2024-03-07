package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.RepairParts
import org.springframework.data.jpa.repository.JpaRepository

interface RepairPartsRepository : JpaRepository<RepairParts, Long> {
}