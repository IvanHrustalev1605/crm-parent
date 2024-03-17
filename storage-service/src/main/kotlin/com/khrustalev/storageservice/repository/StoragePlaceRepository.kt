package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.dictionary.StoragePlace
import org.springframework.data.jpa.repository.JpaRepository

interface StoragePlaceRepository : JpaRepository<StoragePlace, Long> {
}