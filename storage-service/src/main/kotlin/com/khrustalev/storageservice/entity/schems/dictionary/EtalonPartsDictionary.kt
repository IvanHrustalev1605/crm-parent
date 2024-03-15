package com.khrustalev.storageservice.entity.schems.dictionary

import jakarta.persistence.*

@Entity
@Table(schema = "dictionary")
open class EtalonPartsDictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    @Column
    open var art: String? = null
    @Column
    open var name: String? = null
    @Column
    open var analogName: String? = null

}