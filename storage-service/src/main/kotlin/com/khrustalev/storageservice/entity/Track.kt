package com.khrustalev.storageservice.entity

import jakarta.persistence.*

@Entity(name = "track")
@Table(name = "track")
open class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Column(unique = true)
    open var number: String? =null
    @Column(unique = true)
    open var vinNumber: String? = null

    @ManyToOne
    open var car:Car? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Track

        if (id != other.id) return false
        if (number != other.number) return false
        if (vinNumber != other.vinNumber) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (number?.hashCode() ?: 0)
        result = 31 * result + (vinNumber?.hashCode() ?: 0)
        return result
    }

}