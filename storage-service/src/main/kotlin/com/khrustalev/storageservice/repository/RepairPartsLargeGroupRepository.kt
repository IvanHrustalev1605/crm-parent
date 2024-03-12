package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.dictionary.RepairPartsLargeGroup
import org.springframework.data.jpa.repository.JpaRepository

interface RepairPartsLargeGroupRepository : JpaRepository<RepairPartsLargeGroup, Long> {
}