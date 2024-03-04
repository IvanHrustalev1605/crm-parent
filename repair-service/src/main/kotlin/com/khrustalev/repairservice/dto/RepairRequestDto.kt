package com.khrustalev.repairservice.dto

import java.time.LocalDateTime

open class RepairRequestDto {
    open var id: Long? = null
    open var requestDescription: String? = null
    open var engineerId: Long? = null
    open var carId: Long? = null
    open var repairId: Long? = null
    var createDate: LocalDateTime? = null
    var requestNumber: Long? = null

    override fun toString(): String {
        return "RepairRequestDto(requestDescription=$requestDescription, engineerId=$engineerId, carId=$carId, repairId=$repairId)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RepairRequestDto

        if (requestDescription != other.requestDescription) return false
        if (engineerId != other.engineerId) return false
        if (carId != other.carId) return false
        if (repairId != other.repairId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = requestDescription?.hashCode() ?: 0
        result = 31 * result + (engineerId?.hashCode() ?: 0)
        result = 31 * result + (carId?.hashCode() ?: 0)
        result = 31 * result + (repairId?.hashCode() ?: 0)
        return result
    }

}