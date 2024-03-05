package com.khrustalev.storageservice.dto

import com.khrustalev.storageservice.entity.PersonInfo
import com.khrustalev.storageservice.entity.enums.EmployeePosition
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.Driver}
 */
data class DriverDto(
    val id: Long? = null,
    val carId: Long? = null,
    val personInfo: PersonInfo? = null,
    val license: String? = null,
    val position: EmployeePosition? = null,
    val timeToMakeRequestStart: LocalDateTime? = null,
    val timeToMakeRequestEnd: LocalDateTime? = null
) : Serializable