package com.khrustalev.storageservice.entity.schems.storage

import com.khrustalev.storageservice.entity.enums.EmployeePosition
import jakarta.persistence.*

@Entity
@Table(schema = "storage")
open class Engineer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Embedded
    open var personInfo: PersonInfo? = null

    @Enumerated(EnumType.ORDINAL)
    open val employeePosition: EmployeePosition = EmployeePosition.ENGINEER

    @OneToMany(mappedBy = "engineer")
    open var repairRequests: MutableList<RepairRequest>? = null
}