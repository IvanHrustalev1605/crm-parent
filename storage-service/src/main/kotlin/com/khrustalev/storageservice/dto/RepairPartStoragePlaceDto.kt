package com.khrustalev.storageservice.dto

import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.RepairPartStoragePlace}
 */
data class RepairPartStoragePlaceDto(
    val id: Long = 0,
    val repairPartsId: Long? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val inPlace: Boolean? = null,
    val takeAway: LocalDateTime? = null,
    val mechanicId: Long? = null,
    val storagePlaceId: Long? = null
) : Serializable