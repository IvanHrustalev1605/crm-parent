package com.khrustalev.storageservice.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
open class RepairRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Column
    open var requestDescription: String? = null
    @Column
    open var createDate: LocalDateTime? = null
    @Column(unique = true)
    open var requestNumber: Long? = null
    @ManyToOne
    open var engineer: Engineer? = null

    @ManyToOne
    open var car:Car? = null

    @ManyToOne
    open var repair: Repair? = null

}