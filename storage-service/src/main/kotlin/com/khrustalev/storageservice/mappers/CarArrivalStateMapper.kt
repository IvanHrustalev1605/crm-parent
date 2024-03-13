package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.CarArrivalStateDto
import com.khrustalev.storageservice.entity.schems.storage.CarArrivalState
import com.khrustalev.storageservice.service.abstracts.CarService
import com.khrustalev.storageservice.service.abstracts.EngineerService
import com.khrustalev.storageservice.service.abstracts.SecurityService
import org.springframework.stereotype.Component

@Component
class CarArrivalStateMapper(private val engineerService: EngineerService,
                            private val carService: CarService,
                            private val securityService: SecurityService
) {
    fun toDto(carArrivalState: CarArrivalState) : CarArrivalStateDto = CarArrivalStateDto(
        id = carArrivalState.id,
        arrivalTime = carArrivalState.arrivalTime,
        needRepair = carArrivalState.needRepair,
        descriptionProblems = carArrivalState.descriptionProblems,
        engineerId = carArrivalState.engineer!!.id,
        carId = carArrivalState.car!!.id,
        securityId = carArrivalState.receivingSecurity!!.id,
        repairRequestWriteUpTo = carArrivalState.repairRequestWriteUpTo,
        stateChangeTime = carArrivalState.stateChangeTime,
        repairRequestWritten = carArrivalState.repairRequestWritten,
        repairRequestWrittenIn = carArrivalState.repairRequestWrittenIn
    )
    fun toEntity(carArrivalStateDto: CarArrivalStateDto) : CarArrivalState = CarArrivalState(
        id = carArrivalStateDto.id,
        arrivalTime = carArrivalStateDto.arrivalTime!!,
        needRepair = carArrivalStateDto.needRepair,
        descriptionProblems = carArrivalStateDto.descriptionProblems,
        engineer = if (carArrivalStateDto.engineerId != null) engineerService.findById(carArrivalStateDto.engineerId!!) else null,
        car = if (carArrivalStateDto.carId != null) carService.findById(carArrivalStateDto.carId!!) else null,
        receivingSecurity = if (carArrivalStateDto.securityId != null) securityService.findById(carArrivalStateDto.securityId) else null,
        repairRequestWriteUpTo = carArrivalStateDto.arrivalTime!!.plusHours(1),
        stateChangeTime = carArrivalStateDto.stateChangeTime!!,
        repairRequestWritten = carArrivalStateDto.repairRequestWritten,
        repairRequestWrittenIn = carArrivalStateDto.repairRequestWrittenIn
    )
}