package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.RepairRequestDto
import java.time.LocalDateTime

interface RepairRequestService {
    fun createRepairRequest(repairDescription: String, engineerId: Long, carNumber: String, repairProcessId: Long?, requestNumber: Long) : RepairRequestDto
    fun getAllRepairRequestsByCarNumber(carNumber: String) : MutableList<RepairRequestDto>?
    fun getActualRepairRequest(actualDate: LocalDateTime, carNumber: String) : MutableList<Long>
    fun agreedRepairRequest(id: Long) : Boolean

}