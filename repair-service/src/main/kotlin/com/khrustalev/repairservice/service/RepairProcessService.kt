package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.RepairProcessDto
import jakarta.annotation.Nullable
import jakarta.validation.constraints.Null

interface RepairProcessService {
    fun createNewRepairProcess(repairInfoDto: RepairInfoDto, repairRequestList: MutableList<Long>) : RepairProcessDto?
    fun updateRepairProcess(repairProcessId: Long, repairInfoDto: RepairInfoDto,repairRequestList: MutableList<Long>?, newRepairProcessState: Int) : Boolean
    fun closeRepairProcess(repairProcessId: Long, repairInfoDto: RepairInfoDto) : Boolean
    fun takeRepairToWork(repairProcessId: Long) : Boolean
}