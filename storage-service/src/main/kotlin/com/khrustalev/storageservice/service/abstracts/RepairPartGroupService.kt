package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.RepairPartsGroupDto
import com.khrustalev.storageservice.entity.RepairPartsGroup

interface RepairPartGroupService {
    fun findAllGroups() : MutableList<RepairPartsGroup>
    fun findById(id: Long) : RepairPartsGroupDto
    fun save(repairPartsGroupDto: RepairPartsGroupDto): Boolean
}