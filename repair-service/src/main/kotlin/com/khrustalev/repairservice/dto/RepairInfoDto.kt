package com.khrustalev.repairservice.dto

import java.util.UUID

class RepairInfoDto {
    val mechanicIds: MutableList<Long>? = null
    val repairStateNumber: Int = 0
    val repairProcessStateNumber: Int = 0
    val application: String = ""
    val repairPartsNumbers: MutableList<UUID> = mutableListOf()
    val repairProblems: String = ""
    val engineerId: Long? = null
    val carNumber: String? = null
    val carId: Long? = null
    val repairBoxNumber: Int = 0
}