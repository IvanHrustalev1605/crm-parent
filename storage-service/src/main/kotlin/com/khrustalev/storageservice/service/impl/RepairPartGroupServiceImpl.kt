package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.RepairPartsGroupDto
import com.khrustalev.storageservice.entity.RepairPartsGroup
import com.khrustalev.storageservice.exception.NotFoundEntityException
import com.khrustalev.storageservice.mappers.RepairPartsGroupMapper
import com.khrustalev.storageservice.repository.RepairPartsGroupRepository
import com.khrustalev.storageservice.service.abstracts.RepairPartGroupService
import org.springframework.stereotype.Service

@Service
class RepairPartGroupServiceImpl(private val repairPartsGroupRepository: RepairPartsGroupRepository,
                                 private val repairPartsGroupMapper: RepairPartsGroupMapper) : RepairPartGroupService {
    override fun findAllGroups(): MutableList<RepairPartsGroup> {
        return repairPartsGroupRepository.findAll()
    }

    override fun save(repairPartsGroupDto: RepairPartsGroupDto): Boolean {
        repairPartsGroupRepository.save(repairPartsGroupMapper.toEntity(repairPartsGroupDto))
        return true
    }

    override fun findById(id: Long): RepairPartsGroupDto {
        return repairPartsGroupMapper.toDto(repairPartsGroupRepository.findById(id).orElseThrow { NotFoundEntityException("RepairPArtsGroup by id $id not found!") })
    }
}