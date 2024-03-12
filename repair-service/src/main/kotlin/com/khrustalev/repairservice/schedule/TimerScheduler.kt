package com.khrustalev.repairservice.schedule

import com.khrustalev.repairservice.service.CarArrivalStateService
import com.khrustalev.repairservice.service.TimerService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TimerScheduler(private val timerService: TimerService,
                     private val carArrivalStateService: CarArrivalStateService
) {
    @Scheduled(fixedDelay = 2000)
    fun checkTime() {
        carArrivalStateService.getArrivalStatesWithNoRepairRequest().forEach {
            timerService.createRepairRequestTimer(it.key, it.value)
        }
    }
}