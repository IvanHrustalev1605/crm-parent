package com.khrustalev.storageservice.entity.schems.storage

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(schema = "storage")
open class CarLongRepairState(
    @Id
    open var id: Long = 0,
    @MapsId
    @OneToOne
    open var repair: Repair?,

    @Column
    open var createdAt: LocalDateTime,
    @Column
    open var expectedEnd: LocalDateTime,

    @OneToMany
    @JoinTable(schema = "storage")
    open var longRepairEvent: MutableList<LongRepairEvent>

) {
}