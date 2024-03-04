package com.khrustalev.storageservice.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "car_arrival_state")
@JsonIgnoreProperties(ignoreUnknown = true)
open class CarArrivalState(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,
    @Column
    open var arrivalTime: LocalDateTime? = null,
    @Column
    open var needRepair: Boolean? = null,
    @Column
    open var descriptionProblems: String? = null,
    @Column
    open var mileage: Int? = null,

    //пока что просто true или false
    @Column
    open var checkUp: Boolean? = null,
    @Column
    open var stateChangeTime: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    open var receivingSecurity: Security? = null,
    @OneToOne
    open var engineer: Engineer? = null,
    @ManyToOne
    open var car: Car? = null
) {

}