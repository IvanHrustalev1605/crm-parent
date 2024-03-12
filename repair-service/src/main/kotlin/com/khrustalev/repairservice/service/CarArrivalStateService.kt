package com.khrustalev.repairservice.service

import java.time.LocalDateTime

interface CarArrivalStateService {
    fun getArrivalStatesWithNoRepairRequest() : MutableMap<String, LocalDateTime>
}
