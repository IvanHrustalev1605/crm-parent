package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.RepairBoxDto
import com.khrustalev.storageservice.entity.schems.storage.RepairBox
import com.khrustalev.storageservice.service.abstracts.CarRepairStateService
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils
import kotlin.streams.toList

@Component
class RepairBoxMapper(private val repairStateService: CarRepairStateService) {
    fun toDto(repairBox: RepairBox) : RepairBoxDto = RepairBoxDto(
        id = repairBox.id,
        boxNumber = repairBox.boxNumber,
        isFree = repairBox.isFree,
        carRepairStateIds = repairBox.carRepairState.stream().mapToLong { it.id!! }.toList().toMutableList()
    )

    fun toEntity(repairBoxDto: RepairBoxDto) : RepairBox = RepairBox(
        id = repairBoxDto.id,
        boxNumber = repairBoxDto.boxNumber!!,
        isFree = repairBoxDto.isFree,
        carRepairState = if (!CollectionUtils.isEmpty(repairBoxDto.carRepairStateIds)) repairBoxDto.carRepairStateIds!!.stream()
            .map { repairStateService.findById(it!!) }.toList().toMutableList()
            else mutableListOf()
    )
}