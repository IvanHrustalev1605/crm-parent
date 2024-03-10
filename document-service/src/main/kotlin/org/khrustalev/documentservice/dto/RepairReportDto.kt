package org.khrustalev.documentservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class RepairReportDto {
    @JsonProperty(value = "repairRequestIds")
    var repairRequestIds: MutableList<Long> = mutableListOf()
    @JsonProperty(value = "carRepairStatesIds")
    var carRepairStatesIds: MutableList<Long> = mutableListOf()
    @JsonProperty(value = "mechanicIds")
    var mechanicIds: MutableList<Long> = mutableListOf()
}