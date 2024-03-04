package com.khrustalev.storageservice.entity

import jakarta.persistence.*

@Entity
open class RepairRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Column
    open var requestDescription: String? = null

    @ManyToOne
    open var engineer: Engineer? = null

    @ManyToOne
    open var car:Car? = null

    @OneToOne(mappedBy = "repairRequest")
    open var repair: Repair? = null

}