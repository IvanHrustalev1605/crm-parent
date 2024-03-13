package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.RepairRequestDto
import com.khrustalev.storageservice.entity.schems.storage.RepairRequest
import com.khrustalev.storageservice.service.abstracts.CarService
import com.khrustalev.storageservice.service.abstracts.EngineerService
import org.springframework.stereotype.Component

@Component
class RepairRequestMapper(private val carService: CarService,
                          private val engineerService: EngineerService) {
    fun toDto(repairRequest: RepairRequest): RepairRequestDto = RepairRequestDto(
        requestDescription = repairRequest.requestDescription,
        engineerId = repairRequest.engineer!!.id,
        carId = repairRequest.car!!.id,
        id = repairRequest.id,
        createDate = repairRequest.createDate,
        requestNumber = repairRequest.requestNumber,
        agreed = repairRequest.agreed
    )
    fun toEntity(repairRequestDto: RepairRequestDto) : RepairRequest = RepairRequest().also {
        it.id = repairRequestDto.id
        it.car = if (repairRequestDto.carId != null) carService.findById(repairRequestDto.carId!!) else null
        it.engineer = if (repairRequestDto.engineerId != null) engineerService.findById(repairRequestDto.engineerId!!) else null
        it.requestDescription = repairRequestDto.requestDescription
        it.createDate = repairRequestDto.createDate
        it.requestNumber = repairRequestDto.requestNumber
        it.agreed = repairRequestDto.agreed
    }
}