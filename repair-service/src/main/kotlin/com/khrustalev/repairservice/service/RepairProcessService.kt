package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.FullInfoRepairDto
import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.RepairProcessDto
import org.springframework.util.MultiValueMap

interface RepairProcessService {
    fun createNewRepairProcess(repairInfoDto: RepairInfoDto, repairRequestList: MutableList<Long>) : RepairProcessDto?
    fun updateRepairProcess(repairProcessId: Long, repairInfoDto: RepairInfoDto,repairRequestList: MutableList<Long>?) : Boolean
    fun closeRepairProcess(repairProcessId: Long, repairInfoDto: RepairInfoDto) : Boolean
    fun takeRepairToWork(repairProcessId: Long) : Boolean
    fun getAllRepairs(): MutableList<FullInfoRepairDto>
}