package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.RepairReportDto

interface DocumentService {
    fun generateRepairReport(repairId: Long) : RepairReportDto
}