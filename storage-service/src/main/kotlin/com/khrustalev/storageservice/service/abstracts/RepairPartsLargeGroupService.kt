package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.entity.schems.dictionary.RepairPartsLargeGroup

interface RepairPartsLargeGroupService {
    fun findById(repairPartsLargeGroupId: Long): RepairPartsLargeGroup?
}