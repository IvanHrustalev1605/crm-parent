package com.khrustalev.repairservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class RepairBoxDto {
    @JsonProperty(value = "id")
    val id: Long? = null
    @JsonProperty(value = "boxNumber")
    val boxNumber: Int? = null
    @JsonProperty(value = "isFree")
    var isFree: Boolean = false
    @JsonProperty(value = "carRepairStateIds")
    val carRepairStateIds: MutableList<Long> = mutableListOf()
}
