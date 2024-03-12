package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.service.TelegramService
import com.khrustalev.repairservice.service.TimerService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class TimerServiceImpl(private val telegramService: TelegramService) : TimerService {

    override fun createRepairRequestTimer(driverLicense: String, timeToWriteRequest: LocalDateTime) {
        val diff = ChronoUnit.MINUTES.between(timeToWriteRequest, LocalDateTime.now())
                if (diff <= 30) {
                    telegramService.sendMessage("Водителю с $driverLicense осталось менее 30мин что бы оформить заявку на ремонт!")
                }
                if (diff <= 15) {
                    telegramService.sendMessage("Водителю с $driverLicense осталось менее 15мин что бы оформить заявку на ремонт!\n" +
                            "Поторопись ленивая сучка!")
                }
    }
}