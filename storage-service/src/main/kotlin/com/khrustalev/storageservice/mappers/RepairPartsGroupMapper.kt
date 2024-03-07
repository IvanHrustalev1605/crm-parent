package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.RepairPartsGroupDto
import com.khrustalev.storageservice.entity.RepairPartsGroup
import com.khrustalev.storageservice.service.abstracts.RepairPartsService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils
import kotlin.streams.toList
@Component
class RepairPartsGroupMapper(@Lazy private val repairPartsService: RepairPartsService,
                             @Lazy private val repairPartsMapper: RepairPartsMapper) {
    fun toDto(repairPartsGroup: RepairPartsGroup) : RepairPartsGroupDto = RepairPartsGroupDto().also {
        it.id = repairPartsGroup.id
        it.groupName = repairPartsGroup.groupName
        it.stockBalance = repairPartsGroup.stockBalance
        it.repairPartsListIds = repairPartsGroup.repairPartsList.stream().mapToLong { it.id!! }.toList().toMutableList()
    }
    fun toEntity(repairPartsGroupDto: RepairPartsGroupDto) : RepairPartsGroup = RepairPartsGroup().also {
        it.id = repairPartsGroupDto.id
        it.groupName = repairPartsGroupDto.groupName
        it.stockBalance = repairPartsGroupDto.stockBalance
        it.repairPartsList = if (!CollectionUtils.isEmpty(repairPartsGroupDto.repairPartsListIds)) repairPartsGroupDto.repairPartsListIds.stream()
            .map { repairPartsMapper.toEntity(repairPartsService.getById(it)) }
            .toList()
            .toMutableList() else mutableListOf()
    }
}