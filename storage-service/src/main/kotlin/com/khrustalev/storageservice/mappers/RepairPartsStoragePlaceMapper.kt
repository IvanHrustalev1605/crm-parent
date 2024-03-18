package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.RepairPartStoragePlaceDto
import com.khrustalev.storageservice.entity.schems.storage.RepairPartStoragePlace
import com.khrustalev.storageservice.service.abstracts.MechanicService
import com.khrustalev.storageservice.service.abstracts.RepairPartsService
import com.khrustalev.storageservice.service.abstracts.StoragePlaceService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class RepairPartsStoragePlaceMapper(@Lazy private val mechanicService: MechanicService,
                                    @Lazy private val repairPartsService: RepairPartsService,
                                    @Lazy private val storagePlaceService: StoragePlaceService) {
    fun toDto(repairPartStoragePlace: RepairPartStoragePlace) : RepairPartStoragePlaceDto = RepairPartStoragePlaceDto().also {
        it.inPlace = repairPartStoragePlace.inPlace
        it.storagePlaceId = repairPartStoragePlace.storagePlace!!.id
        it.id = repairPartStoragePlace.id
        it.createdAt = repairPartStoragePlace.createdAt
        it.updatedAt = repairPartStoragePlace.updatedAt
        it.mechanicId = repairPartStoragePlace.mechanic!!.id
        it.repairPartsId = repairPartStoragePlace.repairParts!!.id
        it.takeAway = repairPartStoragePlace.takeAway
    }
    fun toEntity(dto: RepairPartStoragePlaceDto) : RepairPartStoragePlace = RepairPartStoragePlace().also {
        it.id = dto.id
        it.createdAt = dto.createdAt
        it.updatedAt = dto.updatedAt
        it.inPlace = dto.inPlace
        it.takeAway = dto.takeAway
        it.mechanic = if (dto.mechanicId != null) mechanicService.getByIds(mutableListOf(dto.mechanicId!!)).first() else null
        it.repairParts = if (dto.repairPartsId != null) repairPartsService.getById(dto.repairPartsId!!) else null
        it.storagePlace = if (dto.storagePlaceId != null) storagePlaceService.getById(dto.storagePlaceId!!) else null
    }
}