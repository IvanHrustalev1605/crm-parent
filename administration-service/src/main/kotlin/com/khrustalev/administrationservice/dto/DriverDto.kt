package com.khrustalev.administrationservice.dto

import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.Driver}
 */
data class DriverDto(
    val id: Long? = null,
    val carId: Long? = null,
    val personInfo: PersonInfoDto? = null,
    val license: String? = null,
    val position: Int? = null,
    val timeToMakeRequestStart: LocalDateTime? = null,
    val timeToMakeRequestEnd: LocalDateTime? = null
) : Serializable