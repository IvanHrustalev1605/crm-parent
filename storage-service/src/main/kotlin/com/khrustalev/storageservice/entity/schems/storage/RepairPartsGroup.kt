package com.khrustalev.storageservice.entity.schems.storage

import com.khrustalev.storageservice.entity.schems.dictionary.RepairPartsLargeGroup
import jakarta.persistence.*

@Entity
@Table(schema = "storage")
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

    @ManyToMany
    @JoinTable(schema = "storage")
    open var repairPartsLargeGroup: MutableList<RepairPartsLargeGroup> = mutableListOf()

}