package com.khrustalev.administrationservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class RepairInfoDto {
    @Schema(description = "ID механиков")
    @JsonProperty(value = "mechanicIds")
    val mechanicIds: MutableList<Long>? = null
    @JsonProperty(value = "repairStateNumber")
    @Schema(description = "Состояние стадии ремонта(enum) NEW(0), TAKE_TO_WORK(1), DIAGNOSTICS(2),SPARE_PARTS_ORDERING(3),REPAIR_PROCESS(4),DONE(5),UNPLANNED_PROBLEMS(6),LONG_TIME_REPAIR(7)")
    val repairStateNumber: Int? = null
    @JsonProperty(value = "repairProcessStateNumber")
    @Schema(description = "Состояние Процесса Ремонта (enum)     NEW(0),IN_REPAIR(1),PROBLEMS(2),DONE(3)")
    val repairProcessStateNumber: Int? = null
    @JsonProperty(value = "application")
    @Schema(description = "Описание проблем или причины постановки на ремонт")
    val application: String? = null
    @JsonProperty(value = "repairPartsNumbers")
    @Schema(description = "Номера(UUID) запчастей")
    val repairPartsNumbers: MutableList<UUID>? = null
    @JsonProperty(value = "repairProblems")
    @Schema(description = "Описание проблем в процессе ремонта")
    val repairProblems: String? = null
    @JsonProperty(value = "engineerId")
    @Schema(description = "ID инженера")
    val engineerId: Long? = null
    @JsonProperty(value = "carNumber")
    @Schema(description = "Номер машины")
    val carNumber: String? = null
    @JsonProperty(value = "carId")
    @Schema(description = "ID машины")
    val carId: Long? = null
    @JsonProperty(value = "repairBoxNumber")
    @Schema(description = "Номер бокса для ремонта")
    val repairBoxNumber: Int? = null
}