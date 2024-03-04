package com.khrustalev.storageservice.entity

import com.khrustalev.storageservice.entity.enums.EmployeePosition
import jakarta.persistence.*

@Entity
open class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Embedded
    open var personInfo: PersonInfo? = null

    @Enumerated(EnumType.ORDINAL)
    open var employeePosition: EmployeePosition = EmployeePosition.MECHANIC

    @ManyToOne
    open var carRepairState: CarRepairState? = null



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Mechanic

        if (id != other.id) return false
        if (personInfo != other.personInfo) return false
        if (employeePosition != other.employeePosition) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (personInfo?.hashCode() ?: 0)
        result = 31 * result + employeePosition.hashCode()
        return result
    }

}