package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.RepairRequestDto

interface RepairRequestService {
    fun createRepairRequest(repairRequestDto: RepairRequestDto) : Boolean
    fun getAllRepairRequestByCarNumber(carNumber: String) : MutableList<RepairRequestDto>?
}
