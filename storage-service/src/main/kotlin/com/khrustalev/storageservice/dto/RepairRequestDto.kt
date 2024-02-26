package com.khrustalev.storageservice.dto

import java.io.Serializable

/**
 * DTO for {@link com.khrustalev.storageservice.entity.RepairRequest}
 */
data class RepairRequestDto(val id: Long? = null, val carId: Long? = null, val repairId: Long? = null) : Serializable