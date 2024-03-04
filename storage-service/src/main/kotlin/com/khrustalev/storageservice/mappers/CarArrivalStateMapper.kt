package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.CarArrivalStateDto
import com.khrustalev.storageservice.entity.CarArrivalState
import com.khrustalev.storageservice.service.CarService
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
        mileage = carArrivalState.mileage,
        checkUp = carArrivalState.checkUp,
        engineerId = carArrivalState.engineer!!.id,
        carId = carArrivalState.car!!.id,
        securityId = carArrivalState.receivingSecurity!!.id
    )
    fun toEntity(carArrivalStateDto: CarArrivalStateDto) : CarArrivalState = CarArrivalState(
        id = carArrivalStateDto.id,
        arrivalTime = carArrivalStateDto.arrivalTime,
        needRepair = carArrivalStateDto.needRepair,
        descriptionProblems = carArrivalStateDto.descriptionProblems,
        mileage = carArrivalStateDto.mileage,
        checkUp = carArrivalStateDto.checkUp,
        engineer = if (carArrivalStateDto.engineerId != null) engineerService.findById(carArrivalStateDto.engineerId!!) else null,
        car = if (carArrivalStateDto.carId != null) carService.findById(carArrivalStateDto.carId!!) else null,
        receivingSecurity = if (carArrivalStateDto.securityId != null) securityService.findById(carArrivalStateDto.securityId) else null
    )
}