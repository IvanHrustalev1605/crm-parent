package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.LongRepairProcessDto
import com.khrustalev.storageservice.entity.schems.storage.CarLongRepairState
import com.khrustalev.storageservice.entity.schems.storage.LongRepairProcess
import com.khrustalev.storageservice.service.abstracts.CarLongRepairStatesService
import com.khrustalev.storageservice.service.abstracts.RepairService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils

@Component
class LongRepairProcessMapper(@Lazy private val repairService: RepairService,
                              @Lazy private val repairMapper: RepairMapper,
                              @Lazy private val carLongRepairStatesService: CarLongRepairStatesService) {
    fun toDto(entity: LongRepairProcess) : LongRepairProcessDto = LongRepairProcessDto(
        id = entity.id!!,
        repairId = entity.repair?.id,
        createdAt = entity.createdAt,
        updatedAt = entity.updatedAt,
        reasons = entity.reasons,
        descriptionProblems = entity.descriptionProblems,
        expectedEnd = entity.expectedEnd,
        timeContainsInMinutes = entity.timeContainsInMinutes,
        carLongRepairStateIds = entity.carLongRepairState?.map { it.id!! }?.toMutableList() ?: mutableListOf()
    )
    fun toEntity(dto: LongRepairProcessDto) : LongRepairProcess = LongRepairProcess(
        id = dto.id,
        repair = repairMapper.toEntity(repairService.findRepairById(dto.repairId!!)!!),
        createdAt = dto.createdAt!!,
        updatedAt = dto.updatedAt!!,
        reasons = dto.reasons!!,
        descriptionProblems = dto.descriptionProblems!!,
        expectedEnd = dto.expectedEnd!!,
        timeContainsInMinutes = dto.timeContainsInMinutes!!,
        carLongRepairState = dto.carLongRepairStateIds?.mapNotNullTo(mutableListOf()) {
            carLongRepairStatesService.findById(it)
        } ?: mutableListOf()
    )
}