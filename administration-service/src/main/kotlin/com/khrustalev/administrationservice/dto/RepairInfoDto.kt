package com.khrustalev.administrationservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class RepairInfoDto {
    @JsonProperty(value = "mechanicIds")
    val mechanicIds: MutableList<Long>? = null
    @JsonProperty(value = "repairStateNumber")
    val repairStateNumber: Int? = null
    @JsonProperty(value = "repairProcessStateNumber")
    val repairProcessStateNumber: Int? = null
    @JsonProperty(value = "application")
    val application: String? = null
    @JsonProperty(value = "repairPartsNumbers")
    val repairPartsNumbers: MutableList<UUID>? = null
    @JsonProperty(value = "repairProblems")
    val repairProblems: String? = null
    @JsonProperty(value = "engineerId")
    val engineerId: Long? = null
    @JsonProperty(value = "carNumber")
    val carNumber: String? = null
    @JsonProperty(value = "carId")
    val carId: Long? = null
    @JsonProperty(value = "repairBoxNumber")
    val repairBoxNumber: Int? = null
}