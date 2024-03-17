package com.khrustalev.storageservice.entity.schems.storage

import com.khrustalev.storageservice.entity.schems.dictionary.StoragePlace
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(schema = "storage")
open class RepairPartStoragePlace {
    @Id
    open var id: Long = 0
    @OneToOne
    @MapsId
    open var repairParts: RepairParts? = null

    @Column
    open var createdAt: LocalDateTime? = null
    @Column
    open var updatedAt: LocalDateTime? = null
    @Column
    open var inPlace: Boolean? = null
    @Column
    open var takeAway: LocalDateTime? = null

    @ManyToOne
    @JoinColumn
    open var mechanic: Mechanic? = null
    @ManyToOne
    @JoinColumn(name = "storage_place_id", referencedColumnName = "id")
    open var storagePlace: StoragePlace? = null
}