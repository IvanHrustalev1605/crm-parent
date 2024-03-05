package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.RepairRequestDto
import com.khrustalev.storageservice.entity.RepairRequest
import java.time.LocalDateTime

interface RepairRequestService {
    fun createRepairRequest(repairRequestDto: RepairRequestDto) : Boolean
    fun getAllRepairRequestByCarNumber(carNumber: String) : MutableList<RepairRequestDto>?
    fun getAllByIds(ids: MutableList<Long>) : MutableList<RepairRequest>
    fun getActualForRepairProcess(actualDate: LocalDateTime, carNumber: String) : MutableList<Long>
    fun takeAgreed(id: Long) : RepairRequestDto?
    fun getByRepairRequestNumber(number: Long): RepairRequestDto
}
