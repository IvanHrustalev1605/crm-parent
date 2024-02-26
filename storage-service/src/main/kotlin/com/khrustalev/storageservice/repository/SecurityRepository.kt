package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.Security
import org.springframework.data.jpa.repository.JpaRepository

interface SecurityRepository : JpaRepository<Security, Long> {
}