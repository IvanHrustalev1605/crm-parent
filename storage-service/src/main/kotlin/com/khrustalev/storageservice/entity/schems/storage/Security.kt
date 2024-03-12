package com.khrustalev.storageservice.entity.schems.storage

import com.khrustalev.storageservice.entity.enums.EmployeePosition
import jakarta.persistence.*

@Entity
@Table(schema = "storage")
open class Security {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Embedded
    open var personInfo: PersonInfo? = null
    @Enumerated
    open var employeePosition: EmployeePosition = EmployeePosition.SECURITY
    @OneToMany(mappedBy = "receivingSecurity")
    open var carArrivalStates: MutableList<CarArrivalState>? = null

}