package org.khrustalev.repairpertsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RepairPertsServiceApplication

fun main(args: Array<String>) {
    runApplication<RepairPertsServiceApplication>(*args)
}
