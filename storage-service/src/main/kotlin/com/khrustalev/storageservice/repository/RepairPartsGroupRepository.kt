package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.RepairPartsGroup
import org.springframework.data.jpa.repository.JpaRepository

interface RepairPartsGroupRepository : JpaRepository<RepairPartsGroup, Long> {
}