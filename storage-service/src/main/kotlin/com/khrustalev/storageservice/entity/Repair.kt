package com.khrustalev.storageservice.entity

import com.khrustalev.storageservice.entity.enums.RepairState
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
open class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Column
    open var carArrivalTime: LocalDateTime? = null
    @Column
    open var endRepair: LocalDateTime? = null

    @Enumerated(EnumType.ORDINAL)
    open var repairState: RepairState? = RepairState.NONE

    @OneToOne
    open var repairRequest:RepairRequest? = null

    @OneToOne
    open var car: Car? = null


}