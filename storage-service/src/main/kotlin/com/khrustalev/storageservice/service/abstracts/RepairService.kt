package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.RepairDto

interface RepairService {
    fun findRepairById(id: Long) : RepairDto?
    fun findRepairByCarNumberAndActualTrue(carNumber: String): RepairDto?
    fun save(repairDto: RepairDto) : Boolean
}
