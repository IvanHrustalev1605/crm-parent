package com.khrustalev.administrationservice.dto

import java.io.Serializable

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.RepairBox}
 */
data class RepairBoxDto(
    val id: Long? = null,
    val boxNumber: Int? = null,
    val isFree: Boolean = false,
    val carRepairStateIds: MutableList<Long>?
) : Serializable