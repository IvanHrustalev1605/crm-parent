package com.khrustalev.repairservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RepairServiceApplication

fun main(args: Array<String>) {
    runApplication<RepairServiceApplication>(*args)
}
