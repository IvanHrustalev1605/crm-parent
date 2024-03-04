package com.khrustalev.repairservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.khrustalev.repairservice.dto.enums.RepairState
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
class RepairProcessDto {
    @JsonProperty(value = "carArrivalTime")
    open var carArrivalTime: LocalDateTime? = null
    open var endRepair: LocalDateTime? = null
    open var repairState: RepairState? = RepairState.NONE
    open var repairRequestId: Long? = null
    open var carId: Long? = null
    open var createTime: LocalDateTime? = null
    open var carRepairStateDtoList: MutableList<CarRepairStateDto>? = mutableListOf()
}
