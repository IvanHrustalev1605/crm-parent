package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.CarRepairStateDto
import com.khrustalev.storageservice.entity.CarRepairState
import com.khrustalev.storageservice.service.CarService
import com.khrustalev.storageservice.service.abstracts.EngineerService
import com.khrustalev.storageservice.service.abstracts.MechanicService
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils

@Component
class CarRepairStateMapper(private val carService: CarService,
                           private val engineerService: EngineerService,
                           private val mechanicService: MechanicService
) {
    fun toDto(carRepairState: CarRepairState) : CarRepairStateDto = CarRepairStateDto(
        id = carRepairState.id,
        repairState = carRepairState.repairState,
        stateChangeTime = carRepairState.stateChangeTime,
        application = carRepairState.application,
        repairParts = carRepairState.repairParts,
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
        repairParts = carRepairStateDto.repairParts,
        repairProblems = carRepairStateDto.repairProblems,
        car = if (carRepairStateDto.carId != null) carService.findById(carRepairStateDto.carId) else null,
        engineer = if (carRepairStateDto.engineerId != null) engineerService.findById(carRepairStateDto.engineerId) else null,
        mechanics = if (!CollectionUtils.isEmpty(carRepairStateDto.mechanicIds)) carRepairStateDto.mechanicIds?.let {
            mechanicService.getByIds(
                it
            )
        } else null
    )
}