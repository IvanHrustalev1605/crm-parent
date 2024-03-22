package com.khrustalev.storageservice.dto

import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.CarLongRepairState}
 */
data class CarLongRepairStateDto(
    val id: Long = 0,
    val repairId: Long? = null,
    val createdAt: LocalDateTime? = null,
    val expectedEnd: LocalDateTime? = null,
    val longRepairEventIds: MutableList<Long>?
) : Serializable