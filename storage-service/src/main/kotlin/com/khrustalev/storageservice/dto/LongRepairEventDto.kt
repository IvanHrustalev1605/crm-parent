package com.khrustalev.storageservice.dto

import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.LongRepairEvent}
 */
data class LongRepairEventDto(
    val id: Long? = null,
    val description: String? = null,
    val createdAt: LocalDateTime? = null,
    val reasonForDelay: String? = null,
    val responsibleId: Long? = null
) : Serializable