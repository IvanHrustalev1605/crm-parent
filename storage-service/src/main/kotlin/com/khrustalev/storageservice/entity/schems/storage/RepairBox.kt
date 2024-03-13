package com.khrustalev.storageservice.entity.schems.storage

import jakarta.persistence.*

@Entity
@Table(schema = "storage")
open class RepairBox(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,
    @Column
    open var boxNumber: Int,
    @Column
    open var isFree: Boolean = true,

    @OneToMany(mappedBy = "repairBox")
    open var carRepairState: MutableList<CarRepairState>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RepairBox

        if (id != other.id) return false
        if (boxNumber != other.boxNumber) return false
        if (isFree != other.isFree) return false
        if (carRepairState != other.carRepairState) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + boxNumber
        result = 31 * result + isFree.hashCode()
        result = 31 * result + carRepairState.hashCode()
        return result
    }
}