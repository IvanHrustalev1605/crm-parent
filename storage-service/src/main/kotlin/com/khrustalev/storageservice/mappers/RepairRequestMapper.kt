package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.RepairRequestDto
import com.khrustalev.storageservice.entity.RepairRequest
import com.khrustalev.storageservice.service.CarService
import com.khrustalev.storageservice.service.abstracts.EngineerService
import com.khrustalev.storageservice.service.abstracts.RepairService
import org.springframework.stereotype.Component

@Component
class RepairRequestMapper(private val repairService: RepairService,
                          private val carService: CarService,
                          private val engineerService: EngineerService) {
    fun toDto(repairRequest: RepairRequest): RepairRequestDto = RepairRequestDto(
        requestDescription = repairRequest.requestDescription,
        engineerId = repairRequest.engineer!!.id,
        carId = repairRequest.car!!.id,
        repairId = repairRequest.repair!!.id,
        id = repairRequest.id,
        createDate = repairRequest.createDate,
        requestNumber = repairRequest.requestNumber
    )
    fun toEntity(repairRequestDto: RepairRequestDto) : RepairRequest = RepairRequest().also {
        it.id = repairRequestDto.id
        it.repair = if (repairRequestDto.repairId != null) repairService.findRepairById(repairRequestDto.repairId!!) else null
        it.car = if (repairRequestDto.carId != null) carService.findById(repairRequestDto.carId!!) else null
        it.engineer = if (repairRequestDto.engineerId != null) engineerService.findById(repairRequestDto.engineerId!!) else null
        it.requestDescription = repairRequestDto.requestDescription
        it.createDate = repairRequestDto.createDate
        it.requestNumber = repairRequestDto.requestNumber
    }
}