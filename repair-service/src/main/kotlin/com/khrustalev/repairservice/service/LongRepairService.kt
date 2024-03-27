package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.LongRepairProcessDto
import com.khrustalev.repairservice.dto.RepairInfoDto

interface LongRepairService {
    fun createNewLongRepairProcess(repairInfoDto: RepairInfoDto, repairId: Long) : LongRepairProcessDto?
    fun updateLongRepairProcess(repairInfoDto: RepairInfoDto) : LongRepairProcessDto?
    fun endLongRepairProcess(repairInfoDto: RepairInfoDto) : LongRepairProcessDto?
}