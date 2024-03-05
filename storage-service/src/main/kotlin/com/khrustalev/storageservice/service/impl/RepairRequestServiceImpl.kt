package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.RepairRequestDto
import com.khrustalev.storageservice.entity.RepairRequest
import com.khrustalev.storageservice.mappers.RepairRequestMapper
import com.khrustalev.storageservice.repository.RepairRequestRepository
import com.khrustalev.storageservice.service.abstracts.RepairRequestService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service("storage-repair-request")
class RepairRequestServiceImpl(private val repairRequestRepository: RepairRequestRepository,
                               @Lazy private val repairRequestMapper: RepairRequestMapper) : RepairRequestService {
    private val LOGGER: Logger = LoggerFactory.getLogger(RepairRequestServiceImpl::class.java)

    override fun createRepairRequest(repairRequestDto: RepairRequestDto): Boolean {
        repairRequestRepository.save(repairRequestMapper.toEntity(repairRequestDto))
        return true
    }

    override fun getAllRepairRequestByCarNumber(carNumber: String): MutableList<RepairRequestDto>? {
        return repairRequestRepository.findAllByCar_Number(carNumber).stream()
            .map { repairRequestMapper.toDto(it) }
            ?.toList()
            ?.toMutableList()
    }

    override fun getAllByIds(ids: MutableList<Long>): MutableList<RepairRequest> {
        return repairRequestRepository.findAllById(ids)
    }

    override fun getActualForRepairProcess(actualDate: LocalDateTime, carNumber: String): MutableList<Long> {
        return repairRequestRepository.getActual(actualDate, carNumber).stream().map { it.id!! }.toList().toMutableList()
    }

    override fun takeAgreed(id: Long): RepairRequestDto? {
        val optionalRequest = repairRequestRepository.findById(id)
        if (optionalRequest.isPresent) {
            val request = optionalRequest.get()
            request.agreed = true
            repairRequestRepository.save(request)
            LOGGER.info("Заявка №${request.requestNumber} согласованна!")
            return repairRequestMapper.toDto(request)
        }
        LOGGER.info("No RepairRequest found by ID $id")
        return null
    }

    override fun getByRepairRequestNumber(number: Long): RepairRequestDto {
        return repairRequestMapper.toDto(repairRequestRepository.findByRequestNumber(number))
    }
}