package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.CarRepairStateDto
import com.khrustalev.repairservice.dto.RepairInfoDto

interface CarRepairStateService {
    fun createNewRepairState(carNumber: String, repairInfoDto: RepairInfoDto) : CarRepairStateDto
    fun createChangeRepairState(repairInfoDto: RepairInfoDto): CarRepairStateDto
}