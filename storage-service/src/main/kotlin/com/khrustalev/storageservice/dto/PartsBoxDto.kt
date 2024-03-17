package com.khrustalev.storageservice.dto

import java.io.Serializable

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.PartsBox}
 */
data class PartsBoxDto(val id: Long = 0, val repairPartsId: Long? = null, val stocksInBox: Long? = null) : Serializable