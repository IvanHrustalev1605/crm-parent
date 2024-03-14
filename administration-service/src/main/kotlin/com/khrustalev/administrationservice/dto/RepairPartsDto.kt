package com.khrustalev.administrationservice.dto

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.RepairParts}
 */
data class RepairPartsDto(
    var id: Long? = null,
    var number: UUID = UUID.randomUUID(),
    var name: String? = null,
    var mileageResource: Long? = null,
    var installedAt: LocalDateTime? = null,
    var installed: Boolean = false,
    var category: Int? = null,
    var carId: Long? = null,
    var repairPartGroupId: Long? = null
) : Serializable