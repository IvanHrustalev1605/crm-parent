package com.khrustalev.storageservice.dto

import com.khrustalev.storageservice.entity.enums.RepairState
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.Repair}
 */
data class RepairDto(
    var id: Long? = null,
    var carArrivalTime: LocalDateTime? = null,
    var endRepair: LocalDateTime? = null,
    var repairState: RepairState? = RepairState.NEW,
    var repairRequestIds: MutableList<Long> = mutableListOf(),
    var carId: Long? = null,
    var actual: Boolean? = null,
    var carRepairState: MutableList<Long>? = null
) : Serializable {

}