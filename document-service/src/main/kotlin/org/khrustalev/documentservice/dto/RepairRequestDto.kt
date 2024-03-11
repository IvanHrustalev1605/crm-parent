package org.khrustalev.documentservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
@JsonIgnoreProperties(ignoreUnknown = true)
class RepairRequestDto(
    @JsonProperty(value = "id")
    var id: Long? = null,
    @JsonProperty(value = "requestDescription")
    var requestDescription: String? = null,
    @JsonProperty(value = "engineerId")
    var engineerId: Long? = null,
    @JsonProperty(value = "carId")
    var carId: Long? = null,
    @JsonProperty(value = "repairId")
    var repairId: Long? = null,
    @JsonProperty(value = "createDate")
    var createDate: LocalDateTime? = null,
    @JsonProperty(value = "requestNumber")
    var requestNumber: Long? = null,
    @JsonProperty(value = "agreed")
    var agreed: Boolean? = false)
