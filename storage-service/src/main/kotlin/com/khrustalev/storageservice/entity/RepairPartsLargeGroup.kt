package com.khrustalev.storageservice.entity

import jakarta.persistence.*

@Entity
open class RepairPartsLargeGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,
    @Column
    open var name: String,

    @ManyToMany
    @JoinTable
    open var repairPartGroups: MutableList<RepairPartsGroup>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RepairPartsLargeGroup

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}