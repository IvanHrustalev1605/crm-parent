package org.khrustalev.repairpartsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class RepairPartsServiceApplication

fun main(args: Array<String>) {
    runApplication<RepairPartsServiceApplication>(*args)
}
