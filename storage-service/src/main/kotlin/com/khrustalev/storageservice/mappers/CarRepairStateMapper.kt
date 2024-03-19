package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.CarRepairStateDto
import com.khrustalev.storageservice.entity.schems.storage.CarRepairState
import com.khrustalev.storageservice.service.abstracts.*
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils
import kotlin.streams.toList

@Component
class CarRepairStateMapper(@Lazy private val carService: CarService,
                           @Lazy private val engineerService: EngineerService,
                           @Lazy private val mechanicService: MechanicService,
                           @Lazy private val repairPartsService: RepairPartsService,
                           @Lazy private val repairBoxService: RepairBoxService,
                           @Lazy private val repairBoxMapper: RepairBoxMapper,
                           @Lazy private val carRepairStateService: CarRepairStateService
) {
    fun toDto(carRepairState: CarRepairState) : CarRepairStateDto = CarRepairStateDto(
        id = carRepairState.id,
        repairState = carRepairState.repairState,
        stateChangeTime = carRepairState.createdAt,
        application = carRepairState.application,
        repairParts = carRepairState.repairParts?.stream()?.mapToLong{ it.id!! }?.toList(),
        repairProblems = carRepairState.repairProblems,
        carId = carRepairState.car!!.id,
        mechanicIds = carRepairState.mechanics?.stream()?.map { it.id!! }?.toList(),
        engineerId = carRepairState.engineer!!.id,
        repairBoxId = carRepairState.repairBox?.id,
        carRepairStateParentId = carRepairState.carRepairStateParent?.id
    )
    fun toEntity(carRepairStateDto: CarRepairStateDto): CarRepairState = CarRepairState(
        id = carRepairStateDto.id,
        repairState = carRepairStateDto.repairState,
        createdAt = carRepairStateDto.stateChangeTime,
        application = carRepairStateDto.application,
        repairParts = if (!CollectionUtils.isEmpty(carRepairStateDto.repairParts!!))
            carRepairStateDto.repairParts.stream()
                .map { repairPartsService.getById(it)!! }
                .toList().toMutableList()
        else mutableListOf(),
        repairProblems = carRepairStateDto.repairProblems,
        car = if (carRepairStateDto.carId != null) carService.findById(carRepairStateDto.carId) else null,
        engineer = if (carRepairStateDto.engineerId != null) engineerService.findById(carRepairStateDto.engineerId) else null,
        mechanics = if (!CollectionUtils.isEmpty(carRepairStateDto.mechanicIds))
            carRepairStateDto.mechanicIds?.let {
            mechanicService.getByIds(it)
        } else null,
        repairBox = if (carRepairStateDto.repairBoxId != null) repairBoxMapper.toEntity(repairBoxService.getById(carRepairStateDto.repairBoxId)) else null,
        carRepairStateParent = if(carRepairStateDto.carRepairStateParentId != null) carRepairStateService.findById(carRepairStateDto.carRepairStateParentId) else null
    )
}