package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.CarRepairStateDto
import com.khrustalev.storageservice.dto.RepairDto

interface RepairService {
    fun findRepairById(id: Long) : RepairDto?
    fun findRepairByCarNumberAndActualTrue(carNumber: String): RepairDto?
    fun save(repairDto: RepairDto) : Long
    fun getAllRepairParts(repairId: Long) : List<String>
    fun findAllRepairStates(repairId: Long): MutableList<CarRepairStateDto>?
    fun getAll(): MutableList<RepairDto>
}
