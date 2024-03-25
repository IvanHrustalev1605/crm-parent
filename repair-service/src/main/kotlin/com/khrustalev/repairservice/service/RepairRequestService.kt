package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.RepairRequestDto
import com.khrustalev.repairservice.dto.RepairRequestQuestionerDto
import java.time.LocalDateTime

interface RepairRequestService {
    fun createRepairRequest(repairRequestQuestionerDto: RepairRequestQuestionerDto) : RepairRequestDto
    fun getAllRepairRequestsByCarNumber(carNumber: String) : MutableList<RepairRequestDto>?
    fun getActualRepairRequest(actualDate: LocalDateTime, carNumber: String) : MutableList<Long>
    fun agreedRepairRequest(id: Long) : Boolean

}