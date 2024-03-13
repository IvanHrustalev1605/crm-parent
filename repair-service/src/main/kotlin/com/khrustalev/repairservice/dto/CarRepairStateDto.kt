package com.khrustalev.repairservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.khrustalev.repairservice.dto.enums.RepairState
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
class CarRepairStateDto {
    @JsonProperty(value = "id")
    var id: Long? = null
    @JsonProperty(value = "repairState")
    var repairState: RepairState? = null
    @JsonProperty(value = "stateChangeTime")
    var stateChangeTime: LocalDateTime = LocalDateTime.now()
    @JsonProperty(value = "application")
    var application: String? = null
    @JsonProperty(value = "repairParts")
    var repairParts: MutableList<Long> = mutableListOf()
    @JsonProperty(value = "repairProblems")
    var repairProblems: String? = null
    @JsonProperty(value = "carId")
    var carId: Long? = null
    @JsonProperty(value = "mechanicIds")
    var mechanicIds: MutableList<Long>? = null
    @JsonProperty(value = "engineerId")
    var engineerId: Long? = null
    @JsonProperty(value = "engineerId")
    var repairBoxId: Long? = null
}