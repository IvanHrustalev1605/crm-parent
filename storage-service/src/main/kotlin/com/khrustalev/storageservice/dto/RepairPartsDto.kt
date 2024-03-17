package com.khrustalev.storageservice.dto

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.RepairParts}
 */
data class RepairPartsDto(
    val id: Long? = null,
    val number: UUID = UUID.randomUUID(),
    val name: String? = null,
    val mileageResource: Long? = null,
    val installedAt: LocalDateTime? = null,
    val isOrigin: Boolean? = null,
    val installed: Boolean = false,
    val vendorArt: String = "",
    val repairPartsLargeGroupId: Long? = null,
    val etalonPartsDictionaryId: Long? = null,
    val carId: Long? = null
) : Serializable