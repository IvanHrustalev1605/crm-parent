package com.khrustalev.storageservice.entity

import com.khrustalev.storageservice.entity.enums.EmployeePosition
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "driver")
@Table(name = "driver")
open class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Embedded
    open var personInfo:PersonInfo? = null
    @Column
    open var license: String? = null

    @Enumerated(EnumType.ORDINAL)
    open val position:EmployeePosition = EmployeePosition.DRIVER

    @Column
    open var timeToMakeRequestStart: LocalDateTime? = null
    @Column
    open var timeToMakeRequestEnd: LocalDateTime? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Driver

        if (id != other.id) return false
        if (personInfo != other.personInfo) return false
        if (license != other.license) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (personInfo?.hashCode() ?: 0)
        result = 31 * result + (license?.hashCode() ?: 0)
        return result
    }

}