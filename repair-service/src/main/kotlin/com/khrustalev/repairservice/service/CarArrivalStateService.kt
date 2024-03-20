package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.CarArrivalStateDto

interface CarArrivalStateService {
    fun sendNotification15minutes()
    fun sendNotification30minutes()
    fun setRepairRequest(carNumber: String) : Boolean
    fun getStateByCarNumber(carNumber: String) : CarArrivalStateDto?
    fun carGetAway(carNumber: String) : Boolean
    fun getActualArrivalStateByCarId(carId: Long) : CarArrivalStateDto?
}
