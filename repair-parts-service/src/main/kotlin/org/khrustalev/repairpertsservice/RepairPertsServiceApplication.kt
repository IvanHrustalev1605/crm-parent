package org.khrustalev.repairpertsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class RepairPertsServiceApplication

fun main(args: Array<String>) {
    runApplication<RepairPertsServiceApplication>(*args)
}
