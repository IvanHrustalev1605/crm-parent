package com.khrustalev.storageservice.entity

import com.khrustalev.storageservice.entity.enums.RepairPartsCategory
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
open class RepairParts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Column(unique = true)
    open var number: UUID = UUID.randomUUID()
    @Column
    open var name: String? = null
    @Column
    open var mileageResource: Long? = null
    @Column
    open var installedAt: LocalDateTime? = null

    @Column
    open var installed: Boolean = false

    @Enumerated(EnumType.ORDINAL)
    open var category: RepairPartsCategory? = null

    @ManyToOne
    open var car: Car? = null
    @ManyToOne
    open var repairPartsGroup: RepairPartsGroup? = null
}