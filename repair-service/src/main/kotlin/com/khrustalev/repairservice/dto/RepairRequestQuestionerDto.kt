package com.khrustalev.repairservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class RepairRequestQuestionerDto {
    @JsonProperty(value = "repairDescription")
    val repairDescription: String? = null
    @JsonProperty(value = "engineerId")
    val engineerId: Long? = null
    @JsonProperty(value = "carNumber")
    val carId:Long? = null
    @JsonProperty(value = "repairProcessId")
    val repairProcessId: Long? = null
}