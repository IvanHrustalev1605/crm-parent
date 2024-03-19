package com.khrustalev.repairservice.schedule

import com.khrustalev.repairservice.service.CarArrivalStateService
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@EnableAsync
class TimerScheduler(private val carArrivalStateService: CarArrivalStateService) {

    @Scheduled(fixedRate = 10 * 60 * 1000)
    @Async
    fun checkTime15() {
        carArrivalStateService.sendNotification15minutes()
    }
    @Scheduled(fixedRate = 10 * 60 * 1000)
    @Async
    fun checkTime30() {
        carArrivalStateService.sendNotification30minutes()
    }
}