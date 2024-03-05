package com.khrustalev.repairservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.khrustalev.repairservice.dto.enums.RepairProcessState
import com.khrustalev.repairservice.dto.enums.RepairState
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
open class RepairProcessDto {
    @JsonProperty(value = "carArrivalTime")
    open var carArrivalTime: LocalDateTime? = null
    @JsonProperty(value = "endRepair")
    open var endRepair: LocalDateTime? = null
    @JsonProperty(value = "repairProcessState")
    open var repairProcessState: RepairProcessState? = null
    @JsonProperty(value = "repairRequestIds")
    open var repairRequestIds: MutableList<Long>? = null
    @JsonProperty(value = "carId")
    open var carId: Long? = null
    @JsonProperty(value = "createTime")
    open var createTime: LocalDateTime? = null
    @JsonProperty(value = "carRepairState")
    open var carRepairStatesIds: MutableList<Long>? = mutableListOf()
    @JsonProperty(value = "actual")
    open var actual: Boolean? = null
}
