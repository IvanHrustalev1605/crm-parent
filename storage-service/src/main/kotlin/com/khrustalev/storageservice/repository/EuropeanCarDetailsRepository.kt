package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.dictionary.EuropeanCarDetails
import org.springframework.data.jpa.repository.JpaRepository

interface EuropeanCarDetailsRepository : JpaRepository<EuropeanCarDetails, Long> {
}