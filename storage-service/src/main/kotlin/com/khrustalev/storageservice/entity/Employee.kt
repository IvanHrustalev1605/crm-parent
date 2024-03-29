package com.khrustalev.storageservice.entity

import com.khrustalev.storageservice.entity.enums.EmployeePosition
import jakarta.persistence.*

@Entity
open class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Embedded
    open var personInfo:PersonInfo? = null
    @Enumerated
    open var position:EmployeePosition = EmployeePosition.MECHANIC
}