package com.khrustalev.storageservice.dto

import java.io.Serializable

/**
 * DTO for {@link com.khrustalev.storageservice.entity.Car}
 */
data class CarDto(
    val id: Long? = null,
    val model: String? = null,
    val number: String? = null,
    val vinNumber: String? = null,
    val mileage: Int? = null,
    val driverId: Long? = null,
    val trackIds: MutableList<Long>?
) : Serializable