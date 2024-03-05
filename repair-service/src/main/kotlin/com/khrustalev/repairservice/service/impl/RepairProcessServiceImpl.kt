package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.RepairProcessDto
import com.khrustalev.repairservice.dto.enums.RepairProcessState
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.CarRepairStateService
import com.khrustalev.repairservice.service.RepairProcessService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import java.time.LocalDateTime

@Service
class RepairProcessServiceImpl(private val storageFeignClient: StorageFeignClient,
                               private val carRepairStateService: CarRepairStateService) : RepairProcessService {
    private val LOGGER: Logger = LoggerFactory.getLogger(RepairProcessServiceImpl::class.java)

    override fun createNewRepairProcess(
        repairInfoDto: RepairInfoDto,
        repairRequestList: MutableList<Long>
    ): RepairProcessDto? {
            val repairProcessDto = RepairProcessDto()
            repairProcessDto.carId = storageFeignClient.findCarByCarNumber(repairInfoDto.carNumber!!)
            repairProcessDto.createTime = LocalDateTime.now()
            repairProcessDto.endRepair = LocalDateTime.now().plusHours(24)
            repairProcessDto.actual = true
            repairProcessDto.repairProcessState = RepairProcessState.entries[repairInfoDto.repairProcessStateNumber]
            repairProcessDto.carArrivalTime = LocalDateTime.now().minusMinutes(25)
            repairProcessDto.carRepairStatesIds = mutableListOf(carRepairStateService.createNewRepairState(repairInfoDto))
            repairProcessDto.repairRequestIds = repairRequestList
            return if (storageFeignClient.saveRepairProcess(repairProcessDto)) {
                LOGGER.info("Успешно сохранили repairProcess")
                repairProcessDto
            } else {
                LOGGER.info("При сохранение repairProcess произошла ошибка!")
                null
            }
        }

    override fun updateRepairProcess(
        repairProcessId: Long,
        repairInfoDto: RepairInfoDto,
        repairRequestList: MutableList<Long>?,
        newRepairProcessState: Int): Boolean {
        val repairProcess = storageFeignClient.getRepairProcessById(repairProcessId)
        repairProcess.repairRequestIds = mutableListOf()
        repairProcess.carRepairStatesIds = mutableListOf(carRepairStateService.changeRepairState(repairInfoDto))
        if (!CollectionUtils.isEmpty(repairRequestList)) repairRequestList?.stream()?.forEach { repairProcess.repairRequestIds?.add(it) }
        repairProcess.repairProcessState = RepairProcessState.entries[newRepairProcessState]
        return storageFeignClient.saveRepairProcess(repairProcess)
    }

}