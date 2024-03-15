package com.khrustalev.repairservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class CarDto {
    @JsonProperty(value = "id")
    val id: Long? = null
    @JsonProperty(value = "model")
    val model: String? = null
    @JsonProperty(value = "number")
    val number: String? = null
    @JsonProperty(value = "vinNumber")
    val vinNumber: String? = null
    @JsonProperty(value = "mileage")
    val mileage: Int? = null
    @JsonProperty(value = "carClassificationId")
    val carClassificationId: Long? = null
    @JsonProperty(value = "driverId")
    val driverId: Long? = null

}
