package com.khrustalev.administrationservice.service

import com.khrustalev.administrationservice.dto.AcceptablePartsDto
import com.khrustalev.administrationservice.dto.RepairPartsDto

interface RepairPartsServiceService {
    fun putPartsToStoragePlace(acceptablePartsDtoList: MutableList<AcceptablePartsDto>) : Boolean
    fun takePartsFromStoragePlace(map: MutableMap<Long, Long>) : Boolean
    fun getInstalledPartsDuringRepair(repairId: Long): MutableList<RepairPartsDto>
}