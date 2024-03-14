package com.khrustalev.administrationservice.dto

import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.RepairRequest}
 */
data class RepairRequestDto(
    var id: Long? = null,
    var requestDescription: String? = null,
    var engineerId: Long? = null,
    var carId: Long? = null,
    var repairId: Long? = null,
    var createDate: LocalDateTime? = null,
    var requestNumber: Long? = null,
    var agreed: Boolean? = false,
) : Serializable