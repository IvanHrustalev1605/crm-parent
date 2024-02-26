package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.Mechanic
import org.springframework.data.jpa.repository.JpaRepository

interface MechanicRepository : JpaRepository<Mechanic, Long> {
}