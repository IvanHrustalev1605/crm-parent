package com.khrustalev.storageservice.dto

import com.khrustalev.storageservice.entity.enums.EmployeePosition
import java.io.Serializable

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.Mechanic}
 */
data class MechanicDto(
    val id: Long? = null,
    val personInfo: PersonInfoDto? = null,
    val employeePosition: EmployeePosition? = null
) : Serializable