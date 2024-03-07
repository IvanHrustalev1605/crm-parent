package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.RepairPartsDto
import com.khrustalev.storageservice.entity.RepairParts

interface RepairPartsService {
    fun getById(id: Long) : RepairPartsDto
    fun save(repairPartsDto: RepairPartsDto) : RepairPartsDto
    fun getByIds(ids: MutableList<Long>) : MutableList<RepairParts>
    fun countStocksPArts() : MutableMap<String, Long>
}