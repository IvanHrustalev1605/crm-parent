package com.khrustalev.storageservice.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
open class RepairParts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Column
    open var number: String? = null
    @Column
    open var name: String? = null
    @Column
    open var mileageResource: Long? = null
    @Column
    open var installedAt: LocalDateTime? = null
    @Column
    open var maxTimeReplace: LocalDateTime? = null
}