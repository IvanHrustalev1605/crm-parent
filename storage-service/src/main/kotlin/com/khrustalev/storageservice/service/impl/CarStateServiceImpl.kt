package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.CarArrivalStateDto
import com.khrustalev.storageservice.dto.CarRepairStateDto
import com.khrustalev.storageservice.mappers.CarArrivalStateMapper
import com.khrustalev.storageservice.mappers.CarRepairStateMapper
import com.khrustalev.storageservice.repository.CarArrivalStateRepository
import com.khrustalev.storageservice.repository.CarRepairStateRepository
import com.khrustalev.storageservice.service.abstracts.CarStateService
import org.springframework.stereotype.Service

@Service
class CarStateServiceImpl(private val carArrivalStateRepository: CarArrivalStateRepository,
                          private val carArrivalStateMapper: CarArrivalStateMapper,
                          private val carRepairStateMapper: CarRepairStateMapper,
                          private val carRepairStateRepository: CarRepairStateRepository) : CarStateService {


    override fun saveArrivalState(carArrivalStateDto: CarArrivalStateDto): Boolean {
        carArrivalStateRepository.save(carArrivalStateMapper.toEntity(carArrivalStateDto))
        return true
    }

    override fun getCarArrivalStateByCarNumber(carNumber: String): CarArrivalStateDto {
        return carArrivalStateMapper.toDto(carArrivalStateRepository.findByCar_NumberOrderByArrivalTimeDesc(carNumber))
    }

    override fun saveRepairState(carRepairStateDto: CarRepairStateDto): Boolean {
        carRepairStateRepository.save(carRepairStateMapper.toEntity(carRepairStateDto))
        return true
    }
}