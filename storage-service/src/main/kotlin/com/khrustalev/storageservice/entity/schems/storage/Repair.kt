package com.khrustalev.storageservice.entity.schems.storage

import com.khrustalev.storageservice.entity.enums.RepairProcessState
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(schema = "storage")
open class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Column
    open var endRepair: LocalDateTime? = null
    @Column
    open var actual: Boolean? = null
    @Column
    open var actualCompletionTime : LocalDateTime? = null
    @Column
    open var differenceWorkTime : Long? = null

    @Enumerated(EnumType.ORDINAL)
    open var repairProcessState: RepairProcessState? = null

    @OneToMany(targetEntity = RepairRequest::class)
    @JoinTable(schema = "storage")
    open var repairRequests:MutableList<RepairRequest> = mutableListOf()

    @OneToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(schema = "storage")
    open var carRepairState: MutableList<CarRepairState>? = null

    @ManyToOne(cascade = [CascadeType.ALL])
    open var car: Car? = null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Repair

        if (id != other.id) return false
        if (endRepair != other.endRepair) return false
        if (actual != other.actual) return false
        if (actualCompletionTime != other.actualCompletionTime) return false
        if (differenceWorkTime != other.differenceWorkTime) return false
        if (repairProcessState != other.repairProcessState) return false
        if (repairRequests != other.repairRequests) return false
        if (carRepairState != other.carRepairState) return false
        if (car != other.car) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (endRepair?.hashCode() ?: 0)
        result = 31 * result + (actual?.hashCode() ?: 0)
        result = 31 * result + (actualCompletionTime?.hashCode() ?: 0)
        result = 31 * result + (differenceWorkTime?.hashCode() ?: 0)
        result = 31 * result + (repairProcessState?.hashCode() ?: 0)
        result = 31 * result + repairRequests.hashCode()
        result = 31 * result + (carRepairState?.hashCode() ?: 0)
        result = 31 * result + (car?.hashCode() ?: 0)
        return result
    }


}