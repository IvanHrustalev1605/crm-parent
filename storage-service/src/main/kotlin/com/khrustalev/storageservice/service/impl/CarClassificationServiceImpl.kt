package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.schems.dictionary.CarClassification
import com.khrustalev.storageservice.repository.CarClassificationRepository
import com.khrustalev.storageservice.service.abstracts.CarClassificationService
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class CarClassificationServiceImpl(private val carClassificationRepository: CarClassificationRepository) : CarClassificationService {
    override fun getClassificationById(carClassificationId: Long): CarClassification {
        return carClassificationRepository.findById(carClassificationId)
            .orElseThrow { EntityNotFoundException("Nothing found in Car_Classification dictionary by ID: $carClassificationId") }
    }
}