package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.RepairPartsDto
import com.khrustalev.storageservice.entity.schems.storage.RepairParts
import com.khrustalev.storageservice.service.abstracts.CarService
import com.khrustalev.storageservice.service.abstracts.EtalonPartsDictionaryService
import com.khrustalev.storageservice.service.abstracts.RepairPartsLargeGroupService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class RepairPartsMapper(@Lazy private val carService: CarService,
                        @Lazy private val etalonPartsDictionaryService: EtalonPartsDictionaryService,
                        @Lazy private val repairPartsLargeGroupService: RepairPartsLargeGroupService) {
    fun toDto(repairParts: RepairParts) : RepairPartsDto = RepairPartsDto(
        id = repairParts.id,
        number = repairParts.number,
        name = repairParts.name,
        mileageResource = repairParts.mileageResource,
        installedAt = repairParts.installedAt,
        isOrigin = repairParts.isOrigin,
        installed = repairParts.installed,
        vendorArt = repairParts.vendorArt,
        repairPartsLargeGroupId = repairParts.repairPartsLargeGroup?.id,
        etalonPartsDictionaryId = repairParts.etalonPartsDictionary!!.id,
        carId = repairParts.car?.id
    )
    fun toEntity(repairPartsDto: RepairPartsDto) : RepairParts = RepairParts().also {
        it.id = repairPartsDto.id
        it.car = if (repairPartsDto.carId != null) carService.findById(repairPartsDto.carId) else null
        it.etalonPartsDictionary = if (repairPartsDto.etalonPartsDictionaryId != null) etalonPartsDictionaryService.findById(repairPartsDto.etalonPartsDictionaryId) else null
        it.installed = repairPartsDto.installed
        it.installedAt = repairPartsDto.installedAt
        it.isOrigin = repairPartsDto.isOrigin
        it.mileageResource = repairPartsDto.mileageResource
        it.name = repairPartsDto.name
        it.number = repairPartsDto.number
        it.vendorArt = repairPartsDto.vendorArt
        it.repairPartsLargeGroup = if (repairPartsDto.repairPartsLargeGroupId != null) repairPartsLargeGroupService.findById(repairPartsDto.repairPartsLargeGroupId) else null
    }
}