package com.khrustalev.storageservice.dto

import java.io.Serializable
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.LongRepairProcess}
 */
data class LongRepairProcessDto(
    val id: Long = 0,
    val repairId: Long? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val reasons: String? = null,
    val descriptionProblems: String? = null,
    val expectedEnd: LocalDateTime? = null,
    val timeContainsInMinutes: Long? = null,
    val carLongRepairStateIds: MutableList<Long>?
) : Serializable