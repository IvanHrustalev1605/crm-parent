package org.khrustalev.documentservice.service

interface WordService {
    fun generateWordDocument() : Boolean
    fun generateRepairRequest(repairId: Long) : Boolean
}