package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.entity.Repair

interface RepairService {
    fun findRepairById(id: Long) : Repair?
}
