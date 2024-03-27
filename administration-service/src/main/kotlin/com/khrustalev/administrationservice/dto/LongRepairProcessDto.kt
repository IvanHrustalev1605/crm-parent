package com.khrustalev.administrationservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.LongRepairProcess}
 */
class LongRepairProcessDto() : Serializable {
    @JsonProperty(required = false)
    var id: Long = 0
    @JsonProperty(required = false)
    var repairId: Long? = null
    @JsonProperty(required = false)
    var createdAt: LocalDateTime? = null
    @JsonProperty(required = false)
    var updatedAt: LocalDateTime? = null
    @JsonProperty(required = false)
    var reasons: String? = null
    @JsonProperty(required = false)
    var descriptionProblems: String? = null
    @JsonProperty(required = false)
    var expectedEnd: LocalDateTime? = null
    @JsonProperty(required = false)
    var timeContainsInMinutes: Long = 0
    @JsonProperty(required = false)
    var carLongRepairStateIds: MutableList<Long> = mutableListOf()
}