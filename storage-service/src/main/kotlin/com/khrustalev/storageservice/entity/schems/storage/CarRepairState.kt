package com.khrustalev.storageservice.entity.schems.storage

import com.khrustalev.storageservice.entity.enums.RepairState
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "car_repair_state", schema = "storage")
open class CarRepairState(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,
    @Enumerated(EnumType.ORDINAL)
    open var repairState: RepairState? = null,
    @Column
    open var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column
    open var application:String? = null,
    @Column
    open var repairProblems: String? = null,

    @OneToOne
    @JoinColumn
    open var carRepairStateParent: CarRepairState? = null,

    @ManyToOne
    open var car: Car? = null,

    @ManyToMany
    @JoinTable(schema = "storage")
    open var mechanics: MutableList<Mechanic>? = null,

    @ManyToOne
    open var engineer: Engineer? = null,

    @ManyToMany
    @JoinTable(schema = "storage")
    open var repairParts: MutableList<RepairParts>? = null,

    @ManyToOne
    open var repairBox: RepairBox?


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CarRepairState

        if (id != other.id) return false
        if (repairState != other.repairState) return false
        if (createdAt != other.createdAt) return false
        if (application != other.application) return false
        if (repairParts != other.repairParts) return false
        if (repairProblems != other.repairProblems) return false
        if (car != other.car) return false
        if (engineer != other.engineer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (repairState?.hashCode() ?: 0)
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + (application?.hashCode() ?: 0)
        result = 31 * result + (repairParts?.hashCode() ?: 0)
        result = 31 * result + (repairProblems?.hashCode() ?: 0)
        result = 31 * result + (car?.hashCode() ?: 0)
        result = 31 * result + (engineer?.hashCode() ?: 0)
        return result
    }
}