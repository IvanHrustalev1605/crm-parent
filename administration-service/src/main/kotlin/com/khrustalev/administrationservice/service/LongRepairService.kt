package com.khrustalev.administrationservice.service

import com.khrustalev.administrationservice.dto.LongRepairProcessDto
import com.khrustalev.administrationservice.dto.RepairInfoDto

interface LongRepairService {
    fun create(repairInfoDto: RepairInfoDto, repairId: Long) : LongRepairProcessDto?
    fun update(repairInfoDto: RepairInfoDto) : LongRepairProcessDto?
}