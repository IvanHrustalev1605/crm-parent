package com.khrustalev.storageservice.entity

import jakarta.persistence.*

@Entity
open class RepairPartsGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Column
    open var groupName: String? = null

    //остаток на складе
    @Column
    open var stockBalance: Int? = null

    @ManyToMany(mappedBy = "repairPartGroups")
    open var repairPartsLargeGroup: MutableList<RepairPartsLargeGroup> = mutableListOf()

}