package com.khrustalev.administrationservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class AdministrationServiceApplication

fun main(args: Array<String>) {
    runApplication<AdministrationServiceApplication>(*args)
}
