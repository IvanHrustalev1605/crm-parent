package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.RepairReportDto
import com.khrustalev.storageservice.service.abstracts.CarRepairStateService
import com.khrustalev.storageservice.service.abstracts.DocumentService
import com.khrustalev.storageservice.service.abstracts.RepairService
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class DocumentServiceImpl(private val repairService: RepairService,
                          private val carRepairStateService: CarRepairStateService) : DocumentService {
    override fun generateRepairReport(repairId: Long): RepairReportDto {
        val repairDto = repairService.findRepairById(repairId)
        var repairReportDto = RepairReportDto()
        var mechanicIdsList: MutableList<Long> = mutableListOf()
        repairDto!!.carRepairState!!.forEach{
            val carRepairState = carRepairStateService.findById(it)
            mechanicIdsList.addAll(carRepairState.mechanics!!.stream().mapToLong{ it.id!! }.toList())
        repairReportDto.carRepairStatesIds = repairDto.carRepairState!!.toMutableList()
        repairReportDto.repairRequestIds = repairDto.repairRequestIds
        repairReportDto.mechanicIds = mechanicIdsList
        }
        return repairReportDto
    }
}