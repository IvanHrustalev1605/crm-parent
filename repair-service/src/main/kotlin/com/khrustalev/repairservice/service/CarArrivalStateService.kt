package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.CarArrivalStateDto
import java.time.LocalDateTime

interface CarArrivalStateService {
    fun getArrivalStatesWithNoRepairRequest() : MutableMap<String, LocalDateTime>
    fun setRepairRequest(carNumber: String) : Boolean
    fun getStateByCarNumber(carNumber: String) : CarArrivalStateDto?
}
