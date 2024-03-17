package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.storage.RepairPartStoragePlace
import org.springframework.data.jpa.repository.JpaRepository

interface RepairPartStoragePlaceRepository : JpaRepository<RepairPartStoragePlace, Long> {
}