package com.khrustalev.repairservice.service

interface DocumentService {
    fun generateRepairReport(repairId: Long) : Boolean
}