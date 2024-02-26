package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.Engineer
import org.springframework.data.jpa.repository.JpaRepository

interface EngineerRepository : JpaRepository<Engineer, Long> {
}