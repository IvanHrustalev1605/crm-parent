package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.RepairBoxDto
import com.khrustalev.storageservice.entity.schems.storage.RepairBox
import com.khrustalev.storageservice.mappers.RepairBoxMapper
import com.khrustalev.storageservice.repository.RepairBoxRepository
import com.khrustalev.storageservice.service.abstracts.RepairBoxService
import jakarta.persistence.EntityNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils

@Service
class RepairBoxServiceImpl(private val repairBoxRepository: RepairBoxRepository,
                           private val repairBoxMapper: RepairBoxMapper) : RepairBoxService {

    private val LOGGER: Logger = LoggerFactory.getLogger(RepairBoxServiceImpl::class.java)

    override fun getById(id: Long): RepairBoxDto {
        return repairBoxMapper.toDto(repairBoxRepository.findById(id).orElseThrow { EntityNotFoundException("RepairBox by ID $id not found!") })
    }

    override fun getByNumber(boxNumber: Int): RepairBoxDto {
        return repairBoxMapper.toDto(repairBoxRepository.findByBoxNumber(boxNumber)
            .orElseThrow { EntityNotFoundException("RepairBox by boxNumber $boxNumber not found!") })
    }

    override fun getAllByIds(ids: MutableList<Long>): MutableList<RepairBox> {
        return repairBoxRepository.findAllById(ids)
    }

    override fun mapEntityToDto(entityList: MutableList<RepairBox>): MutableList<RepairBoxDto> {
        return entityList.stream().map { repairBoxMapper.toDto(it) }.toList().toMutableList()
    }

    override fun save(repairBoxDto: RepairBoxDto): RepairBoxDto {
        return repairBoxMapper.toDto(repairBoxRepository.save(repairBoxMapper.toEntity(repairBoxDto)))
    }

    override fun getAllIsFree(): List<RepairBoxDto> {
        if (!CollectionUtils.isEmpty(repairBoxRepository.getAllFreeBoxes())) {
            return repairBoxRepository.getAllFreeBoxes().stream()
                .map { repairBoxMapper.toDto(it) }
                .toList()
        }
            LOGGER.info("На данный момент нет свободных боксов!")
            return emptyList()
    }
}