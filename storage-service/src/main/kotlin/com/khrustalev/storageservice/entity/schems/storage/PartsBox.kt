package com.khrustalev.storageservice.entity.schems.storage

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

/**
 * Используется для подсчета деталей в коробках
 * т.е. в RepairParts -> EtalonPArtsStocks будет лежать количество коробок,
 * а тут количество остатков в 1 коробке
 * */

@Entity
@Table(schema = "storage")
open class PartsBox(
    @Id
    open var id: Long = 0,
    @MapsId
    @OneToOne
    open var repairParts: RepairParts,

    @Column
    open var stocksInBox: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PartsBox

        if (id != other.id) return false
        if (repairParts != other.repairParts) return false
        if (stocksInBox != other.stocksInBox) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + repairParts.hashCode()
        result = 31 * result + stocksInBox.hashCode()
        return result
    }
}