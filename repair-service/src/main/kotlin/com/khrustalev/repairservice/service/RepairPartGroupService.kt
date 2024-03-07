package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.RepairPartsDto

interface RepairPartGroupService {
    fun writeOffRepairPart(repairPartsDto: RepairPartsDto) : Boolean

}