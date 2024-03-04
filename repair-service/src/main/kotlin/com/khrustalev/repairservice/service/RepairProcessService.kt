package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.RepairProcessDto

interface RepairProcessService {
    fun createRepairProcess(carNumber: String, repairInfoDto: RepairInfoDto, repairRequestList: MutableList<Long>) : RepairProcessDto?

}