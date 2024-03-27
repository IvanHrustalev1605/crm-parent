package com.khrustalev.storageservice.dto

import com.khrustalev.storageservice.entity.enums.LongRepairStates
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.CarLongRepairState}
 */
data class CarLongRepairStateDto(
    val id: Long? = null,
    val createdAt: LocalDateTime? = null,
    val repairProblems: String? = null,
    val carStayInBase: Boolean? = null,
    val longRepairStatesId: Int? = null,
    val engineerId: Long? = null,
    val repairPartIds: MutableList<Long>?,
    val mechanicIds: MutableList<Long>?,
    val carLongRepairStateParentId: Long? = null,
    val boxIds: MutableList<Long>?,
    val endAt: LocalDateTime? = null,
    val carId: Long? = null

) : Serializable