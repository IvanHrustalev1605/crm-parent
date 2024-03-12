package com.khrustalev.repairservice

import com.khrustalev.repairservice.service.TelegramService
import com.khrustalev.repairservice.service.impl.TelegramServiceImpl
import com.khrustalev.repairservice.service.impl.TimerServiceImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
class RepairServiceApplication

fun main(args: Array<String>) {
    runApplication<RepairServiceApplication>(*args)
}
