package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.CarArrivalStateDto
import com.khrustalev.storageservice.dto.CarRepairStateDto

interface CarStateService {
    fun saveArrivalState(carArrivalStateDto: CarArrivalStateDto): Boolean
    fun saveRepairState(carRepairStateDto: CarRepairStateDto): Long
    fun getCarArrivalStateByCarNumber(carNumber: String): CarArrivalStateDto
}
