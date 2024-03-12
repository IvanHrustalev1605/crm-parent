package com.khrustalev.storageservice.dto

import java.io.Serializable

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.PersonInfo}
 */
data class PersonInfoDto(
    val firstName: String? = null,
    val middleName: String? = null,
    val lastName: String? = null,
    val age: Int? = null,
    val email: String? = null
) : Serializable