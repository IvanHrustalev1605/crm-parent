package com.khrustalev.administrationservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.RepairParts}
 */
data class RepairPartsDto(
    @JsonProperty(value = "id")
    var id: Long? = null,
    @JsonProperty(value = "number")
    var number: UUID = UUID.randomUUID(),
    @JsonProperty(value = "name")
    var name: String? = null,
    @JsonProperty(value = "mileageResource")
    var mileageResource: Long? = null,
    @JsonProperty(value = "installedAt")
    var installedAt: LocalDateTime? = null,
    @JsonProperty(value = "isOrigin")
    var isOrigin: Boolean? = null,
    @JsonProperty(value = "installed")
    var installed: Boolean = false,
    @JsonProperty(value = "vendorArt")
    var vendorArt: String = "",
    @JsonProperty(value = "repairPartsLargeGroupId")
    var repairPartsLargeGroupId: Long? = null,
    @JsonProperty(value = "etalonPartsDictionaryId")
    var etalonPartsDictionaryId: Long? = null,
    @JsonProperty(value = "carId")
    var carId: Long? = null
) : Serializable