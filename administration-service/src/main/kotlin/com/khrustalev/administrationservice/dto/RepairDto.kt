package com.khrustalev.administrationservice.dto

import com.khrustalev.repairservice.dto.enums.RepairProcessState
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.Repair}
 */
data class RepairDto(
    var id: Long? = null,
    var carArrivalTime: LocalDateTime? = null,
    var endRepair: LocalDateTime? = null,
    var repairProcessState: RepairProcessState? = null,
    var repairRequestIds: MutableList<Long> = mutableListOf(),
    var carId: Long? = null,
    var actual: Boolean? = null,
    var carRepairState: MutableList<Long>? = null,
    var actualCompletionTime : LocalDateTime? = null,
    var differenceWorkTime : Long? = null,
    var repairStartAt: LocalDateTime? = null
) : Serializable {

}