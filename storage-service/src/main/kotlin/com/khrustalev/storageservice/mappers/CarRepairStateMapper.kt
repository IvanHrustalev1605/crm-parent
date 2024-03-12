package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.CarRepairStateDto
import com.khrustalev.storageservice.entity.schems.storage.CarRepairState
import com.khrustalev.storageservice.service.CarService
import com.khrustalev.storageservice.service.abstracts.EngineerService
import com.khrustalev.storageservice.service.abstracts.MechanicService
import com.khrustalev.storageservice.service.abstracts.RepairPartsService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils
import kotlin.streams.toList

@Component
class CarRepairStateMapper(@Lazy private val carService: CarService,
                           @Lazy private val engineerService: EngineerService,
                           @Lazy private val mechanicService: MechanicService,
                           @Lazy private val repairPartsService: RepairPartsService
) {
    fun toDto(carRepairState: CarRepairState) : CarRepairStateDto = CarRepairStateDto(
        id = carRepairState.id,
        repairState = carRepairState.repairState,
        stateChangeTime = carRepairState.stateChangeTime,
        application = carRepairState.application,
        repairParts = carRepairState.repairParts?.stream()?.mapToLong{ it.id!! }?.toList(),
        repairProblems = carRepairState.repairProblems,
        carId = carRepairState.car!!.id,
        mechanicIds = carRepairState.mechanics?.stream()?.map { it.id!! }?.toList(),
        engineerId = carRepairState.engineer!!.id
    )
    fun toEntity(carRepairStateDto: CarRepairStateDto): CarRepairState = CarRepairState(
        id = carRepairStateDto.id,
        repairState = carRepairStateDto.repairState,
        stateChangeTime = carRepairStateDto.stateChangeTime,
        application = carRepairStateDto.application,
        repairParts = if (!CollectionUtils.isEmpty(carRepairStateDto.repairParts))
            carRepairStateDto.repairParts?.let { repairPartsService.getByIds(it.toMutableList()) } else null,
        repairProblems = carRepairStateDto.repairProblems,
        car = if (carRepairStateDto.carId != null) carService.findById(carRepairStateDto.carId) else null,
        engineer = if (carRepairStateDto.engineerId != null) engineerService.findById(carRepairStateDto.engineerId) else null,
        mechanics = if (!CollectionUtils.isEmpty(carRepairStateDto.mechanicIds))
            carRepairStateDto.mechanicIds?.let {
            mechanicService.getByIds(it)
        } else null
    )
}