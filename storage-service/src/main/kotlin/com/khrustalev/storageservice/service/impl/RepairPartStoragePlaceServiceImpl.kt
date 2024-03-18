package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.RepairPartStoragePlaceDto
import com.khrustalev.storageservice.mappers.RepairPartsMapper
import com.khrustalev.storageservice.mappers.RepairPartsStoragePlaceMapper
import com.khrustalev.storageservice.repository.RepairPartStoragePlaceRepository
import com.khrustalev.storageservice.repository.RepairPartsRepository
import com.khrustalev.storageservice.service.abstracts.RepairPartStoragePlaceService
import com.khrustalev.storageservice.service.abstracts.StoragePlaceService
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.Objects

@Service
class RepairPartStoragePlaceServiceImpl(private val repairPartStoragePlaceRepository: RepairPartStoragePlaceRepository,
                                        private val repairPartsStoragePlaceMapper: RepairPartsStoragePlaceMapper) : RepairPartStoragePlaceService {
    override fun putParts(repairPartStoragePlaceDto: RepairPartStoragePlaceDto): Boolean {
        repairPartStoragePlaceRepository.save(repairPartsStoragePlaceMapper.toEntity(repairPartStoragePlaceDto))
        return true
    }

    override fun getById(repairPartId: Long): RepairPartStoragePlaceDto {
        return repairPartsStoragePlaceMapper.toDto(repairPartStoragePlaceRepository.findById(repairPartId)
            .orElseThrow { EntityNotFoundException("Couldn't find the spare part on the storage place by ID: $repairPartId") })
    }

    override fun save(repairPartStoragePlaceDto: RepairPartStoragePlaceDto): Boolean {
        return Objects.nonNull(repairPartStoragePlaceRepository.save(repairPartsStoragePlaceMapper.toEntity(repairPartStoragePlaceDto)))
    }
}