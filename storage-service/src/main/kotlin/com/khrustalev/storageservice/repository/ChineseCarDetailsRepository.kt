package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.dictionary.ChineseCarDetails
import org.springframework.data.jpa.repository.JpaRepository

interface ChineseCarDetailsRepository : JpaRepository<ChineseCarDetails, Long> {
}