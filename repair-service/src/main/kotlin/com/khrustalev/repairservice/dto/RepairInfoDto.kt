package com.khrustalev.repairservice.dto

import java.time.LocalDateTime

class RepairInfoDto {
    val mechanicIds: MutableList<Long>? = null
    val repairStateNumber: Int = 0
    val repairProcessStateNumber: Int = 0
    val application: String = ""
    val repairParts: String = ""
    val repairProblems: String = ""
    val engineerId: Long? = null
    val carNumber: String? = null
    val carId: Long? = null
}