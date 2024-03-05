package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.RepairInfoDto

interface CarRepairStateService {
    fun createNewRepairState(repairInfoDto: RepairInfoDto) : Long
    fun changeRepairState(repairInfoDto: RepairInfoDto): Long
}