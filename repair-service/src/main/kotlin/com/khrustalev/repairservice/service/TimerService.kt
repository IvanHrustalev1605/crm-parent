package com.khrustalev.repairservice.service

import java.time.LocalDateTime

interface TimerService {
    fun createRepairRequestTimer(driverLicense: String, timeToWriteRequest: LocalDateTime)
}