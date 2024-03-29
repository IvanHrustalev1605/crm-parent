package com.khrustalev.storageservice.dto

import com.khrustalev.storageservice.entity.enums.RepairState
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.CarRepairState}
 */
data class CarRepairStateDto(
    val id: Long? = null,
    val repairState: RepairState? = null,
    val stateChangeTime: LocalDateTime = LocalDateTime.now(),
    val application: String? = null,
    val repairParts: String? = null,
    val repairProblems: String? = null,
    val carId: Long? = null,
    val mechanicIds: MutableList<Long>?,
    val engineerId: Long? = null
) : Serializable