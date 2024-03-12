package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.dictionary.SpecialTechniqueDetails
import org.springframework.data.jpa.repository.JpaRepository

interface SpecialTechniqueDetailsRepository : JpaRepository<SpecialTechniqueDetails, Long> {
}