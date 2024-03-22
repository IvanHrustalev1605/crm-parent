package com.khrustalev.storageservice.entity.schems.storage

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(schema = "storage")
open class LongRepairEvent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,
    @Column
    open var description: String,
    @Column
    open var createdAt: LocalDateTime,
    @Column
    open var reasonForDelay: String,

    @ManyToOne
    open var responsible: Engineer
) {

}