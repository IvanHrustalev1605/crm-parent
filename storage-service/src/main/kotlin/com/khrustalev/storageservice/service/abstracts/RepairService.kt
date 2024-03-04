package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.RepairDto
import com.khrustalev.storageservice.entity.Repair

interface RepairService {
    fun findRepairById(id: Long) : Repair?
    fun findRepairByCarNumberAndActualTrue(carNumber: String): RepairDto?
    fun save(repairDto: RepairDto) : Boolean
}
