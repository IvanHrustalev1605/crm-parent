package com.khrustalev.storageservice.dto

import com.khrustalev.storageservice.entity.enums.RepairState
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.Repair}
 */
data class RepairDto(
    val id: Long? = null,
    val carArrivalTime: LocalDateTime? = null,
    val endRepair: LocalDateTime? = null,
    val repairState: RepairState? = RepairState.NONE,
    val repairRequestId: Long? = null,
    val carId: Long? = null
) : Serializable