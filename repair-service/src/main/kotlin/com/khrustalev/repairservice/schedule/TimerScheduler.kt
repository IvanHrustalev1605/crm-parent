package com.khrustalev.repairservice.schedule

import com.khrustalev.repairservice.service.CarArrivalStateService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TimerScheduler(private val carArrivalStateService: CarArrivalStateService) {

    @Scheduled(fixedRate = 6000)
    fun checkTime15() {
        carArrivalStateService.sendNotification15minutes()
    }
    @Scheduled(fixedRate = 5000)
    fun checkTime30() {
        carArrivalStateService.sendNotification30minutes()
    }
}