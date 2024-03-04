package com.khrustalev.repairservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
open class CarArrivalStateDto {
    @JsonProperty(value = "id")
    open var id: Long? = null
    @JsonProperty(value = "carId")
    open var carId: Long? = null
    @JsonProperty(value = "engineerId")
    open var engineerId: Long? = null
    @JsonProperty(value = "needRepair")
    open var needRepair: Boolean? = null
    @JsonProperty(value = "arrivalTime")
    open var arrivalTime: LocalDateTime? = null
    @JsonProperty(value = "descriptionProblems")
    open var descriptionProblems: String? = null
    @JsonProperty(value = "checkUp")
    open var checkUp: Boolean? = null
    @JsonProperty(value = "mileage")
    open var mileage: Int? = null
    /*Охранник который запустил машину*/
    @JsonProperty(value = "receivingSecurity")
    open var receivingSecurity: Long? = null
    override fun toString(): String {
        return "CarStateDto(id=$id, carId=$carId, engineerId=$engineerId, needRepair=$needRepair, arrivalTime=$arrivalTime, descriptionProblems=$descriptionProblems, checkUp=$checkUp)"
    }

}
