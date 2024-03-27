package com.khrustalev.administrationservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Общая информация о ремонте. Учитывая длительный ремонт и обычный")
class FullInfoRepairDto {
    @Schema(description = "Обычный ремонт", required = false)
    @JsonProperty(value = "repairDro", required = false)
    var repairDro: RepairDto? = null
    @JsonProperty(value = "longRepairProcessDto", required = false)
    @Schema(description = "Длительный ремонт", required = false)
    var longRepairProcessDto : LongRepairProcessDto? = null

}