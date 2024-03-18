package com.khrustalev.administrationservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class AcceptablePartsDto {
    @JsonProperty(value = "repairPartsDto")
    var repairPartsDto: RepairPartsDto? = null
    @JsonProperty(value = "storagePlaceId")
    var storagePlaceId: Long? = null
}