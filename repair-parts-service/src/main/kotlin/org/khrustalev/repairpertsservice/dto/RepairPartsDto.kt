package org.khrustalev.repairpertsservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.RepairParts}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class RepairPartsDto(
    @JsonProperty(value = "id")
    val id: Long? = null,
    @JsonProperty(value = "number")
    val number: UUID = UUID.randomUUID(),
    @JsonProperty(value = "name")
    val name: String? = null,
    @JsonProperty(value = "mileageResource")
    val mileageResource: Long? = null,
    @JsonProperty(value = "installedAt")
    val installedAt: LocalDateTime? = null,
    @JsonProperty(value = "isOrigin")
    val isOrigin: Boolean? = null,
    @JsonProperty(value = "installed")
    val installed: Boolean = false,
    @JsonProperty(value = "vendorArt")
    val vendorArt: String = "",
    @JsonProperty(value = "repairPartsLargeGroupId")
    val repairPartsLargeGroupId: Long? = null,
    @JsonProperty(value = "etalonPartsDictionaryId")
    val etalonPartsDictionaryId: Long? = null,
    @JsonProperty(value = "carId")
    val carId: Long? = null
) : Serializable