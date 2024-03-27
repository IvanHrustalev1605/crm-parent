package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.RepairProcessDto

interface CarLongRepairStateService {
    fun createNewLongRepairState(repairInfoDto: RepairInfoDto, repairProcessDto: RepairProcessDto?): Long
    fun updateLongRepairState(repairInfoDto: RepairInfoDto): Long
    fun createEndLongRepairState(repairInfoDto: RepairInfoDto): Long
}