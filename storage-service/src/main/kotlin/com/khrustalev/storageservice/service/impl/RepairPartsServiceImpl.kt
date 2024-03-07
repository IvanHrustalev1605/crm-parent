package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.RepairPartsDto
import com.khrustalev.storageservice.entity.RepairParts
import com.khrustalev.storageservice.exception.NotFoundEntityException
import com.khrustalev.storageservice.mappers.RepairPartsMapper
import com.khrustalev.storageservice.repository.RepairPartsGroupRepository
import com.khrustalev.storageservice.repository.RepairPartsRepository
import com.khrustalev.storageservice.service.abstracts.RepairPartGroupService
import com.khrustalev.storageservice.service.abstracts.RepairPartsService
import org.springframework.stereotype.Service

@Service
class RepairPartsServiceImpl(private val repairPartsRepository: RepairPartsRepository,
                             private val repairPartsMapper: RepairPartsMapper,
                             private val repairPartGroupService: RepairPartGroupService) : RepairPartsService {
    override fun getById(id: Long): RepairPartsDto {
        return repairPartsMapper.toDto(repairPartsRepository.findById(id).orElseThrow { NotFoundEntityException("RepairPart by id $id not found!") })
    }

    override fun save(repairPartsDto: RepairPartsDto): RepairPartsDto {
        return repairPartsMapper.toDto(repairPartsRepository.save(repairPartsMapper.toEntity(repairPartsDto)))
    }

    override fun getByIds(ids: MutableList<Long>): MutableList<RepairParts> {
        return repairPartsRepository.findAllById(ids)
    }

    override fun countStocksPArts(): MutableMap<String, Long> {
        val resultMap: MutableMap<String, Long> = mutableMapOf()
        repairPartGroupService.findAllGroups().forEach {
            val partStocks = repairPartsRepository.customCountStocksParts(it.id!!)
            resultMap[it.groupName!!] = partStocks
        }
        return resultMap
    }
}