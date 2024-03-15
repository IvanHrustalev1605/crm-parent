package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.dictionary.CarClassification
import org.springframework.data.jpa.repository.JpaRepository

interface CarClassificationRepository : JpaRepository<CarClassification, Long> {
}