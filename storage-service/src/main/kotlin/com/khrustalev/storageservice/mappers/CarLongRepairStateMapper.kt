package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.CarLongRepairStateDto
import com.khrustalev.storageservice.entity.enums.LongRepairStates
import com.khrustalev.storageservice.entity.schems.storage.CarLongRepairState
import com.khrustalev.storageservice.entity.schems.storage.RepairBox
import com.khrustalev.storageservice.service.abstracts.*
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils

@Component
class CarLongRepairStateMapper(@Lazy private val engineerService: EngineerService,
                               @Lazy private val repairPartsService: RepairPartsService,
                               @Lazy private val repairPartsMapper: RepairPartsMapper,
                               @Lazy private val mechanicService: MechanicService,
                               @Lazy private val carLongRepairStatesService: CarLongRepairStatesService,
                               @Lazy private val boxService: RepairBoxService,
                               @Lazy private val carService: CarService) {
    fun toDto(carLongRepairState: CarLongRepairState) : CarLongRepairStateDto = CarLongRepairStateDto(
        id = carLongRepairState.id,
        createdAt = carLongRepairState.createdAt,
        repairProblems = carLongRepairState.repairProblems,
        carStayInBase = carLongRepairState.carStayInBase,
        longRepairStatesId = carLongRepairState.longRepairStates.ordinal,
        engineerId = carLongRepairState.engineer!!.id,
        repairPartIds = if (!CollectionUtils.isEmpty(carLongRepairState.repairParts))carLongRepairState.repairParts.map { it.id!! }.toMutableList() else mutableListOf(),
        mechanicIds = if (!CollectionUtils.isEmpty(carLongRepairState.mechanic)) carLongRepairState.mechanic.map { it.id!! }.toMutableList() else mutableListOf(),
        carLongRepairStateParentId = carLongRepairState.carLongRepairStateParent?.id,
        boxIds = carLongRepairState.box?.map { it.id!! }?.toMutableList(),
        endAt = carLongRepairState.endAt,
        carId = carLongRepairState.car?.id
    )
    fun toEntity(dto: CarLongRepairStateDto) : CarLongRepairState = CarLongRepairState(
        id = dto.id,
        createdAt = dto.createdAt!!,
        repairProblems = dto.repairProblems!!,
        carStayInBase = dto.carStayInBase!!,
        longRepairStates = LongRepairStates.entries[dto.longRepairStatesId!!],
        engineer = if (dto.engineerId != null) engineerService.findById(dto.engineerId) else null,
        repairParts = if (!CollectionUtils.isEmpty(dto.repairPartIds)) repairPartsService.getByIds(dto.repairPartIds!!)
            .map { repairPartsMapper.toEntity(it) }
            .toMutableList() else mutableListOf(),
        mechanic = if (!CollectionUtils.isEmpty(dto.mechanicIds)) mechanicService.getByIds(dto.mechanicIds!!) else mutableListOf(),
        carLongRepairStateParent = if (dto.carLongRepairStateParentId != null) carLongRepairStatesService.findById(dto.carLongRepairStateParentId) else null,
        box = if (!CollectionUtils.isEmpty(dto.boxIds)) boxService.getAllByIds(dto.boxIds!!) else mutableListOf(),
        endAt = dto.endAt,
        car = if (dto.carId != null) carService.findById(dto.carId) else null
    )
}