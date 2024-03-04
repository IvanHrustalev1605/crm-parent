package com.khrustalev.storageservice.dto

import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.Car}
 */
data class CarDto(
    val id: Long? = null,
    val model: String? = null,
    val number: String? = null,
    val vinNumber: String? = null,
    val mileage: Int? = null,
    val needRepair: Boolean?,
    val arrivalTime: LocalDateTime? = null
) : Serializable