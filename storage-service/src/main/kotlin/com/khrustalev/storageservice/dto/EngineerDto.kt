package com.khrustalev.storageservice.dto

import com.khrustalev.storageservice.entity.enums.EmployeePosition
import com.khrustalev.storageservice.entity.schems.storage.PersonInfo
import java.io.Serializable

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.Engineer}
 */
data class EngineerDto(
    val id: Long? = null,
    val personInfo: PersonInfo? = null,
    val employeePosition: EmployeePosition? = null
) : Serializable