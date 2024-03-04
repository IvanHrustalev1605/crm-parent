package com.khrustalev.repairservice.dto

class RepairRequestDto {
    open var requestDescription: String? = null
    open var engineerId: Long? = null
    open var carId: Long? = null
    open var repairId: Long? = null
}