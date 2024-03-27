package com.khrustalev.storageservice.entity.schems.storage

import com.khrustalev.storageservice.entity.enums.LongRepairStates
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(schema = "storage")
open class CarLongRepairState(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,
    @Column
    open var createdAt: LocalDateTime,
    @Column
    open var repairProblems: String?,
    @Column
    open var carStayInBase: Boolean,
    @Column
    open var endAt: LocalDateTime?,
    @Enumerated
    open var longRepairStates: LongRepairStates,

    @OneToOne
    open var carLongRepairStateParent: CarLongRepairState?,

    @ManyToOne(fetch = FetchType.LAZY)
    open var engineer: Engineer?,

    @OneToMany
    @JoinTable(schema = "storage")
    open var repairParts: MutableList<RepairParts>,

    @ManyToMany
    @JoinTable(schema = "storage")
    open var mechanic: MutableList<Mechanic>,

    @ManyToMany
    @JoinTable(schema = "storage")
    open var box: MutableList<RepairBox>,

    @ManyToOne
    open var car: Car?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CarLongRepairState

        if (id != other.id) return false
        if (createdAt != other.createdAt) return false
        if (repairProblems != other.repairProblems) return false
        if (carStayInBase != other.carStayInBase) return false
        if (longRepairStates != other.longRepairStates) return false
        if (carLongRepairStateParent != other.carLongRepairStateParent) return false
        if (engineer != other.engineer) return false
        if (repairParts != other.repairParts) return false
        if (mechanic != other.mechanic) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + (repairProblems?.hashCode() ?: 0)
        result = 31 * result + carStayInBase.hashCode()
        result = 31 * result + longRepairStates.hashCode()
        result = 31 * result + (carLongRepairStateParent?.hashCode() ?: 0)
        result = 31 * result + (engineer?.hashCode() ?: 0)
        result = 31 * result + repairParts.hashCode()
        result = 31 * result + mechanic.hashCode()
        return result
    }
}