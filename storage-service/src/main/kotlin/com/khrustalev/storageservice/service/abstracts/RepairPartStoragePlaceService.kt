package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.RepairPartStoragePlaceDto

interface RepairPartStoragePlaceService {
    fun putParts(repairPartStoragePlaceDto: RepairPartStoragePlaceDto) : Boolean
    fun getById(repairPartId: Long): RepairPartStoragePlaceDto
    fun save(repairPartStoragePlaceDto: RepairPartStoragePlaceDto): Boolean
}