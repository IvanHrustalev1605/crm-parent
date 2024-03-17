package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.storage.PartsBox
import org.springframework.data.jpa.repository.JpaRepository

interface PartsBoxRepository : JpaRepository<PartsBox, Long> {
}