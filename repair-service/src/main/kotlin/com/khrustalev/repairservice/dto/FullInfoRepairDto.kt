package com.khrustalev.repairservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

class FullInfoRepairDto {
    @JsonProperty(value = "repairDro", required = false)
    var repairDro: RepairProcessDto? = null
    @JsonProperty(value = "longRepairProcessDto", required = false)
    var longRepairProcessDto : LongRepairProcessDto? = null

}