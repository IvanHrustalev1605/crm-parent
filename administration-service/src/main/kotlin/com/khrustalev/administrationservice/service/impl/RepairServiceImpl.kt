package com.khrustalev.administrationservice.service.impl

import com.khrustalev.administrationservice.dto.*
import com.khrustalev.administrationservice.feign.RepairProcessFeignClient
import com.khrustalev.administrationservice.service.RepairService
import org.springframework.stereotype.Service

@Service
class RepairServiceImpl(private val repairProcessFeignClient: RepairProcessFeignClient) : RepairService {
    override fun securityCheckCar(arrivalQuestionnaire: ArrivalQuestionnaire, securityId: Long): Boolean {
        return repairProcessFeignClient.securityCheckCar(arrivalQuestionnaire, securityId)
    }

    override fun createRepairRequest(
        repairDescription: String,
        engineerId: Long,
        carNumber: String,
        repairProcessId: Long?,
        requestNumber: Long
    ): RepairRequestDto {
        return repairProcessFeignClient.createRepairRequest(repairDescription, engineerId, carNumber, repairProcessId, requestNumber)
    }

    override fun createRepairRequest(repairInfoDto: RepairInfoDto, repairRequestList: MutableList<Long>): RepairDto {
        return repairProcessFeignClient.createRepairRequest(repairInfoDto, repairRequestList)
    }

    override fun takeToRepairRequest(repairProcessId: Long): Boolean {
        return repairProcessFeignClient.takeToRepairRequest(repairProcessId)
    }

    override fun updateRepairRequest(
        repairInfoDto: RepairInfoDto,
        repairRequestList: MutableList<Long>?,
        repairProcessId: Long
    ): Boolean {
        return repairProcessFeignClient.updateRepairRequest(repairInfoDto, repairRequestList, repairProcessId)
    }

    override fun endRepairProcess(repairInfoDto: RepairInfoDto, repairProcessId: Long): Boolean {
        return repairProcessFeignClient.endRepairRequest(repairInfoDto, repairProcessId)
    }

    override fun agreedRepairRequest(id: Long): Boolean {
        return repairProcessFeignClient.agreedRepairRequest(id)
    }

    override fun getRepairReport(repairId: Long): Boolean {
        return repairProcessFeignClient.getRepairReport(repairId)
    }

    override fun getFreeBoxes(): MutableList<RepairBoxDto> {
        return repairProcessFeignClient.getFreeBoxes()
    }

    override fun carGetAway(carNumber: String): Boolean {
        return repairProcessFeignClient.carGetAway(carNumber)
    }
}