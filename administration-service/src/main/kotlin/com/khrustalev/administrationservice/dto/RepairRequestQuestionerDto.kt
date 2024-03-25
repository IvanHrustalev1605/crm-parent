package com.khrustalev.administrationservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@JsonIgnoreProperties(ignoreUnknown = true)
class RepairRequestQuestionerDto {
    @JsonProperty(value = "repairDescription")
    @Schema(description = "Описание ремонта. Что сломалось и тд.", required = true)
    val repairDescription: String? = null
    @JsonProperty(value = "engineerId")
    @Schema(description = "ID инженера ответственного за ремонт", required = true)
    val engineerId: Long? = null
    @JsonProperty(value = "carNumber")
    @Schema(description = "ID машины", required = true)
    val carId:Long? = null
    @JsonProperty(value = "repairProcessId")
    @Schema(description = "ID процесса ремонта. Поле необязательное, " +
            "указывается только если нужно писать дополнительные заявки в процессе ремонта", required = false)
    val repairProcessId: Long? = null
}