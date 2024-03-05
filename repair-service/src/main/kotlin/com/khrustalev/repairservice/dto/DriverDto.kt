package com.khrustalev.repairservice.dto

import java.time.LocalDateTime

class DriverDto {
    val id: Long? = null
    val carId: Long? = null
    val personInfo: PersonInfoDto? = null
    val license: String? = null
    val timeToMakeRequestStart: LocalDateTime? = null
    val timeToMakeRequestEnd: LocalDateTime? = null
}
