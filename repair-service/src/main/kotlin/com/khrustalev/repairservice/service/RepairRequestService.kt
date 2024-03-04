package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.RepairRequestDto

interface RepairRequestService {
    fun createRepairRequest(repairDescription: String, engineerId: Long, carNumber: String, repairProcessId: Long) : RepairRequestDto
    fun getAllRepairRequestsByCarNumber(carNumber: String) : MutableList<RepairRequestDto>?
}