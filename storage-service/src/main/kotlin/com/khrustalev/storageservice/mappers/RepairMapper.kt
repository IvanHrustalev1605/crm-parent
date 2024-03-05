package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.RepairDto
import com.khrustalev.storageservice.entity.Repair
import com.khrustalev.storageservice.service.CarService
import com.khrustalev.storageservice.service.abstracts.CarRepairStateService
import com.khrustalev.storageservice.service.abstracts.RepairRequestService
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils

@Component
class RepairMapper(private val carService: CarService,
                   private val carRepairStateService: CarRepairStateService,
                   private val repairRequestService: RepairRequestService) {
    fun toDto(repair: Repair) : RepairDto = RepairDto().also {
        it.id = repair.id
        it.carId = repair.car?.id
        it.repairRequestIds = repair.repairRequests.stream().map { rd -> rd.id!! }
            .toList()
            .toMutableList()
        it.carRepairState = repair.carRepairState?.stream()?.map { crs -> crs.id!!}?.toList()?.toMutableList()
        it.endRepair = repair.endRepair
        it.repairState = repair.repairState
        it.carArrivalTime = repair.carArrivalTime
        it.actual = repair.actual
    }
    fun toEntity(repairDto: RepairDto) : Repair = Repair().also {
        it.id = repairDto.id
        it.actual = repairDto.actual
        it.carArrivalTime = repairDto.carArrivalTime
        it.repairState = repairDto.repairState
        it.endRepair = repairDto.endRepair
        it.car = if (repairDto.carId != null) carService.findById(repairDto.carId!!) else null
        it.carRepairState = if (!CollectionUtils.isEmpty(repairDto.carRepairState)) repairDto.carRepairState?.let { it1 ->
            carRepairStateService.getDtoById(
                it1
            )
        } else null
        it.repairRequests = if (!CollectionUtils.isEmpty(repairDto.repairRequestIds)) repairRequestService.getAllByIds(repairDto.repairRequestIds) else mutableListOf()
    }
}
