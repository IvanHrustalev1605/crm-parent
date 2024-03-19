package com.khrustalev.storageservice.entity.schems.storage

import com.khrustalev.storageservice.entity.enums.RepairPartsCategory
import com.khrustalev.storageservice.entity.schems.dictionary.EtalonPartsDictionary
import com.khrustalev.storageservice.entity.schems.dictionary.RepairPartsLargeGroup
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(schema = "storage")
open class RepairParts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Column(unique = true, updatable = false)
    open var number: UUID = UUID.randomUUID()
    @Column
    open var name: String? = null
    @Column
    open var mileageResource: Long? = null
    @Column
    open var installedAt: LocalDateTime? = null
    @Column
    open var isOrigin: Boolean? = null
    @Column
    open var installed: Boolean = false
    @Column
    open var vendorArt: String = ""

    @ManyToOne
    @JoinColumn(nullable = false)
    open var repairPartsLargeGroup: RepairPartsLargeGroup? = null
    @ManyToOne
    @JoinColumn(name = "etalon_art", referencedColumnName = "art", nullable = false)
    open var etalonPartsDictionary: EtalonPartsDictionary? = null

    @ManyToOne
    open var car: Car? = null

}