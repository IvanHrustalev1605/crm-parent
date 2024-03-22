package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.CarLongRepairStateDto
import com.khrustalev.storageservice.entity.schems.storage.CarLongRepairState
import com.khrustalev.storageservice.service.abstracts.RepairService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class CarLongRepairStateMapper(@Lazy private val repairService: RepairService,
                               @Lazy private val repairMapper: RepairMapper) {
    fun toDto(carLongRepairState: CarLongRepairState) : CarLongRepairStateDto = CarLongRepairStateDto(
        id = carLongRepairState.id,
        repairId = carLongRepairState.repair!!.id,
        createdAt = carLongRepairState.createdAt,
        expectedEnd = carLongRepairState.expectedEnd,
        longRepairEventIds = carLongRepairState.longRepairEvent.map { it.id!! }.toMutableList()
    )
    fun toEntity(dto: CarLongRepairStateDto) : CarLongRepairState = CarLongRepairState(
        id = dto.id,
        repair = if (dto.repairId != null) repairMapper.toEntity(repairService.findRepairById(dto.repairId)!!) else null,
        createdAt = dto.createdAt!!,
        expectedEnd = dto.expectedEnd!!,
        longRepairEvent = mutableListOf()
    )
}