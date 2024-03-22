package org.khrustalev.repairpartsservice.dto

import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.RepairPartStoragePlace}
 */
data class RepairPartStoragePlaceDto(
    var id: Long = 0,
    var repairPartsId: Long? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var inPlace: Boolean? = null,
    var takeAway: LocalDateTime? = null,
    var mechanicId: Long? = null,
    var storagePlaceId: Long? = null
) : Serializable