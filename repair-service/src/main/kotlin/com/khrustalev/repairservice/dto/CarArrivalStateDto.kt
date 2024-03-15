package com.khrustalev.repairservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
open class CarArrivalStateDto {
    @JsonProperty(value = "id")
    var id: Long? = null
    @JsonProperty(value = "arrivalTime")
    var arrivalTime: LocalDateTime? = null
    @JsonProperty(value = "needRepair")
    var needRepair: Boolean? = null
    @JsonProperty(value = "descriptionProblems")
    var descriptionProblems: String? = null
    @JsonProperty(value = "engineerId")
    var engineerId: Long? = null
    @JsonProperty(value = "carId")
    var carId: Long? = null
    @JsonProperty(value = "receivingSecurity")
    var receivingSecurity: Long? = null
    @JsonProperty(value = "repairRequestWriteUpTo")
    var repairRequestWriteUpTo: LocalDateTime? = null
    @JsonProperty(value = "stateChangeTime")
    var stateChangeTime: LocalDateTime? = null
    @JsonProperty(value = "repairRequestWritten")
    var repairRequestWritten: Boolean = false
    @JsonProperty(value = "repairRequestWrittenIn")
    var repairRequestWrittenIn: LocalDateTime? = null
    @JsonProperty(value = "inBase")
    var inBase: Boolean? = null
    @JsonProperty(value = "timeToMakeRequestStart")
    var timeToMakeRequestStart: LocalDateTime? = null
    @JsonProperty(value = "timeToMakeRequestEnd")
    var timeToMakeRequestEnd: LocalDateTime? = null
    var is15Notificate: Boolean? = null
    @JsonProperty(value = "is30Notificate")
    var is30Notificate: Boolean? = null
}
