package com.khrustalev.repairservice.dto

import java.io.Serializable

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.Engineer}
 */
data class EngineerDto(
    val id: Long? = null,
    val personInfo: PersonInfoDto? = null,
) : Serializable