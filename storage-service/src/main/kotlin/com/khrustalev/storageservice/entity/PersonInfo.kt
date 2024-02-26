package com.khrustalev.storageservice.entity

import jakarta.persistence.Embeddable

@Embeddable
open class PersonInfo {
    open var firstName: String? = null
    open var middleName: String? = null
    open var lastName: String? = null
    open var age: Int? = null
    open var email: String? = null
}