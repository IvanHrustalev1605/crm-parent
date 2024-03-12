package com.khrustalev.storageservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.CarState}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class CarArrivalStateDto(
    @JsonProperty(value = "id")
    var id: Long? = null,
    @JsonProperty(value = "arrivalTime")
    var arrivalTime: LocalDateTime? = null,
    @JsonProperty(value = "needRepair")
    var needRepair: Boolean? = null,
    @JsonProperty(value = "descriptionProblems")
    var descriptionProblems: String? = null,
    @JsonProperty(value = "engineerId")
    var engineerId: Long? = null,
    @JsonProperty(value = "carId")
    var carId: Long? = null,
    @JsonProperty(value = "receivingSecurity")
    var securityId: Long? = null,
    @JsonProperty(value = "repairRequestWriteUpTo")
    var repairRequestWriteUpTo: LocalDateTime? = null,
    @JsonProperty(value = "stateChangeTime")
    var stateChangeTime: LocalDateTime? = null
) : Serializable