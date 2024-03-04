package com.khrustalev.storageservice.entity

import com.khrustalev.storageservice.entity.enums.RepairState
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "car_repair_state")
open class CarRepairState(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,
    @Enumerated(EnumType.ORDINAL)
    open var repairState: RepairState? = null,
    @Column
    open var stateChangeTime: LocalDateTime = LocalDateTime.now(),
    //ну какая то типа заявка или письмо
    @Column
    open var application:String? = null,
    //для простоты пока что просто список запчастей, в дальнейшем уже будет полноценная сущность со своими полями
    @Column
    open var repairParts: String? = null,
    //какие то проблемы возникшие при ремонте на этом state
    @Column
    open var repairProblems: String? = null,

    @ManyToOne
    open var car: Car? = null,

    @ManyToMany
    @JoinTable
    open var mechanics: MutableList<Mechanic>? = null,

    @ManyToOne
    open var engineer: Engineer? = null,
    @ManyToOne(cascade = [CascadeType.ALL])
    open var repair: Repair? = null

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CarRepairState

        if (id != other.id) return false
        if (repairState != other.repairState) return false
        if (stateChangeTime != other.stateChangeTime) return false
        if (application != other.application) return false
        if (repairParts != other.repairParts) return false
        if (repairProblems != other.repairProblems) return false
        if (car != other.car) return false
        if (engineer != other.engineer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (repairState?.hashCode() ?: 0)
        result = 31 * result + stateChangeTime.hashCode()
        result = 31 * result + (application?.hashCode() ?: 0)
        result = 31 * result + (repairParts?.hashCode() ?: 0)
        result = 31 * result + (repairProblems?.hashCode() ?: 0)
        result = 31 * result + (car?.hashCode() ?: 0)
        result = 31 * result + (engineer?.hashCode() ?: 0)
        return result
    }
}