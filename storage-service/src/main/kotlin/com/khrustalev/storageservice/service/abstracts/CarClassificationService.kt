package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.entity.schems.dictionary.CarClassification

interface CarClassificationService {
    fun getClassificationById(carClassificationId: Long): CarClassification

}
