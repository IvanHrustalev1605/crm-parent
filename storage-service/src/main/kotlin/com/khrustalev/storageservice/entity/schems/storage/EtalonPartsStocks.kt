package com.khrustalev.storageservice.entity.schems.storage

import com.khrustalev.storageservice.entity.schems.dictionary.EtalonPartsDictionary
import jakarta.persistence.*


@Entity
@Table(schema = "storage")
open class EtalonPartsStocks {
    @Id
    open val id: Long = 0

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    open var etalonPartsDictionary: EtalonPartsDictionary? = null

    @Column
    open var stocks: Int? = null

}