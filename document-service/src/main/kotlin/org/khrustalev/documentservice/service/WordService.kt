package org.khrustalev.documentservice.service

interface WordService {
    fun generateRepairReport(repairId: Long) : Boolean
}