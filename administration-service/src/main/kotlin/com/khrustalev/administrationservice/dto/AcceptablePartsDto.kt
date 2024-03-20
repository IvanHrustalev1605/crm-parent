package com.khrustalev.administrationservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@JsonIgnoreProperties(ignoreUnknown = true)
class AcceptablePartsDto {
    @JsonProperty(value = "repairPartsDto")
    @Schema(description = "Запчасть")
    var repairPartsDto: RepairPartsDto? = null
    @JsonProperty(value = "storagePlaceId")
    @Schema(description = "ID склада")
    var storagePlaceId: Long? = null
}