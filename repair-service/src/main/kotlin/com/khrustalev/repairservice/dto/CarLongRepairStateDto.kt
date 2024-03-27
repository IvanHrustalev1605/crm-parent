package com.khrustalev.repairservice.dto

import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.CarLongRepairState}
 */
class CarLongRepairStateDto() : Serializable {
    var id: Long = 0
    var createdAt: LocalDateTime? = null
    var repairProblems: String? = null
    var carStayInBase: Boolean? = null
    var longRepairStatesId: Int? = null
    var engineerId: Long? = null
    var repairPartIds: MutableList<Long> = mutableListOf()
    var mechanicIds: MutableList<Long> = mutableListOf()
    var carLongRepairStateParentId: Long? = null
    var boxIds: MutableList<Long> = mutableListOf()
    var endAt: LocalDateTime? = null
    var carId: Long? = null
}