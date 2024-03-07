package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.RepairPartsDto
import com.khrustalev.storageservice.entity.RepairParts
import com.khrustalev.storageservice.entity.enums.RepairPartsCategory
import com.khrustalev.storageservice.service.CarService
import com.khrustalev.storageservice.service.abstracts.RepairPartGroupService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class RepairPartsMapper(@Lazy private val carService: CarService,
                        @Lazy private val repairPartGroupService: RepairPartGroupService,
                        @Lazy private val repairPartsGroupMapper: RepairPartsGroupMapper) {
    fun toDto(repairParts: RepairParts) : RepairPartsDto = RepairPartsDto(
        id = repairParts.id,
        number = repairParts.number,
        name = repairParts.name,
        mileageResource = repairParts.mileageResource,
        installed = repairParts.installed,
        installedAt = repairParts.installedAt,
        category = repairParts.category?.ordinal,
        carId = repairParts.car?.id,
        repairPartGroupId = repairParts.repairPartsGroup!!.id
    )
    fun toEntity(repairPartsDto: RepairPartsDto) : RepairParts = RepairParts().also {
        it.id = repairPartsDto.id
        it.installed = repairPartsDto.installed
        it.name = repairPartsDto.name
        it.category = RepairPartsCategory.entries[repairPartsDto.category!!]
        it.installedAt = repairPartsDto.installedAt
        it.mileageResource = repairPartsDto.mileageResource
        it.number = repairPartsDto.number
        it.car = if (repairPartsDto.carId != null) carService.findById(repairPartsDto.carId!!) else null
        it.repairPartsGroup = repairPartsGroupMapper.toEntity(repairPartGroupService.findById(repairPartsDto.repairPartGroupId!!))
    }
}