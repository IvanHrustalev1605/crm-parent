package com.khrustalev.storageservice.entity.schems.storage

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "car_arrival_state", schema = "storage")
@JsonIgnoreProperties(ignoreUnknown = true)
open class CarArrivalState(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,
    @Column
    open var arrivalTime: LocalDateTime = LocalDateTime.now(),
    @Column
    open var needRepair: Boolean? = null,
    @Column
    open var descriptionProblems: String? = null,
    @Column
    open var repairRequestWriteUpTo: LocalDateTime = arrivalTime.plusHours(1),
    @Column
    open var repairRequestWrittenIn: LocalDateTime? = null,
    @Column
    open var repairRequestWritten: Boolean = false,
    @Column
    open var stateChangeTime: LocalDateTime = LocalDateTime.now(),
    @Column
    open var inBase: Boolean = true,

    @ManyToOne
    open var receivingSecurity: Security? = null,

    @ManyToOne
    open var engineer: Engineer? = null,

    @ManyToOne
    open var car: Car? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CarArrivalState

        if (id != other.id) return false
        if (arrivalTime != other.arrivalTime) return false
        if (needRepair != other.needRepair) return false
        if (descriptionProblems != other.descriptionProblems) return false
        if (repairRequestWriteUpTo != other.repairRequestWriteUpTo) return false
        if (stateChangeTime != other.stateChangeTime) return false
        if (receivingSecurity != other.receivingSecurity) return false
        if (engineer != other.engineer) return false
        if (car != other.car) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + arrivalTime.hashCode()
        result = 31 * result + (needRepair?.hashCode() ?: 0)
        result = 31 * result + (descriptionProblems?.hashCode() ?: 0)
        result = 31 * result + repairRequestWriteUpTo.hashCode()
        result = 31 * result + stateChangeTime.hashCode()
        result = 31 * result + (receivingSecurity?.hashCode() ?: 0)
        result = 31 * result + (engineer?.hashCode() ?: 0)
        result = 31 * result + (car?.hashCode() ?: 0)
        return result
    }

}