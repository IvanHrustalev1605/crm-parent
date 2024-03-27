package com.khrustalev.storageservice.entity.schems.storage

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Entity
@Table(schema = "storage")
open class LongRepairProcess(
    @Id
    open var id: Long? = 0,
    @MapsId
    @OneToOne
    open var repair: Repair?,

    @Column
    open var createdAt: LocalDateTime?,
    @Column
    open var updatedAt: LocalDateTime?,
    @Column
    open var reasons: String?,
    @Column
    open var descriptionProblems: String?,
    @Column
    open var expectedEnd: LocalDateTime?,
    @Column
    open var timeContainsInMinutes: Long?,

    @OneToMany
    @JoinTable(schema = "storage")
    open var carLongRepairState: MutableList<CarLongRepairState>?
) {
}