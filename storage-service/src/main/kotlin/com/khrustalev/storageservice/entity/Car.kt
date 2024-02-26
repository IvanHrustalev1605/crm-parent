package com.khrustalev.storageservice.entity

import jakarta.persistence.*

@Entity(name = "car")
@Table(name = "car")
open class Car() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Column
    open var model: String? = null
    @Column
    open var number: String? = null
    @Column
    open var vinNumber: String? = null
    @Column
    open var mileage: Int? = null

    @OneToOne
    @JoinColumn
    open var driver:Driver? = null

    @OneToMany(mappedBy = "car")
    open var tracks: MutableList<Track>? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Car

        if (id != other.id) return false
        if (model != other.model) return false
        if (number != other.number) return false
        if (vinNumber != other.vinNumber) return false
        if (mileage != other.mileage) return false
        if (driver != other.driver) return false
        if (tracks != other.tracks) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (model?.hashCode() ?: 0)
        result = 31 * result + (number?.hashCode() ?: 0)
        result = 31 * result + (vinNumber?.hashCode() ?: 0)
        result = 31 * result + (mileage ?: 0)
        result = 31 * result + (driver?.hashCode() ?: 0)
        result = 31 * result + (tracks?.hashCode() ?: 0)
        return result
    }

}
