package com.khrustalev.storageservice.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.khrustalev.storageservice.entity.enums.RepairProcessState
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.Repair}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class RepairDto(
    @JsonProperty(required = true)
    var id: Long? = null,
    @JsonProperty(required = true)
    var endRepair: LocalDateTime? = null,
    @JsonProperty(required = true)
    var repairProcessState: RepairProcessState? = null,
    @JsonProperty(required = true)
    var repairRequestIds: MutableList<Long> = mutableListOf(),
    @JsonProperty(required = true)
    var carId: Long? = null,
    @JsonProperty(required = true)
    var actual: Boolean? = null,
    @JsonProperty(required = true)
    var carRepairState: MutableList<Long>? = null,
    @JsonProperty(required = true)
    var actualCompletionTime : LocalDateTime? = null,
    @JsonProperty(required = true)
    var differenceWorkTime : Long? = null,
    @JsonProperty(required = true)
    var repairStartAt: LocalDateTime? = null
) : Serializable {

}