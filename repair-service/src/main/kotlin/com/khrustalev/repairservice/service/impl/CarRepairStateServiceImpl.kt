package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.CarRepairStateDto
import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.enums.RepairState
import com.khrustalev.repairservice.exceptions.SomethingGoWrongException
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.CarRepairStateService
import com.khrustalev.repairservice.service.RepairPartsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CarRepairStateServiceImpl(private val storageFeignClient: StorageFeignClient,
                                private val repairPartsService: RepairPartsService) : CarRepairStateService {
    private val LOGGER: Logger = LoggerFactory.getLogger(CarRepairStateServiceImpl::class.java)

    override fun createNewRepairState(repairInfoDto: RepairInfoDto): Long {
        val carRepairState = CarRepairStateDto()

        val arrivalState = storageFeignClient.getLastArrivalStateByCarNumber(repairInfoDto.carNumber!!)
        if (arrivalState != null) {
            carRepairState.carId = arrivalState.carId
            carRepairState.engineerId = arrivalState.engineerId
            carRepairState.stateChangeTime = LocalDateTime.now()
            carRepairState.repairState = RepairState.NEW
            carRepairState.application = repairInfoDto.application
            carRepairState.mechanicIds = repairInfoDto.mechanicIds
            carRepairState.repairProblems = repairInfoDto.repairProblems
            LOGGER.info("Новая state создана. Начинаем сохранение... $carRepairState")
            return storageFeignClient.saveCarRepairState(carRepairState)
        } else {
            throw SomethingGoWrongException("Возможно эта машина еще не заехала на базу или какая то неточность в переданной информации!")
        }
    }

    override fun changeRepairState(repairInfoDto: RepairInfoDto): Long {
        val carRepairState = CarRepairStateDto()
        carRepairState.repairState = RepairState.entries[repairInfoDto.repairStateNumber]
        carRepairState.repairProblems = repairInfoDto.repairProblems
        carRepairState.stateChangeTime = LocalDateTime.now()
        carRepairState.application = repairInfoDto.application
        carRepairState.mechanicIds = repairInfoDto.mechanicIds
        carRepairState.engineerId = repairInfoDto.engineerId
        carRepairState.repairParts.addAll(repairPartsService.install(repairInfoDto.repairParts, repairInfoDto.carId!!))
        carRepairState.carId = repairInfoDto.carId
        LOGGER.info("State изменения информации о ремонте создана. Начинаем сохранение... $carRepairState")
        return storageFeignClient.saveCarRepairState(carRepairState)
    }

    override fun createEndRepairState(repairProcessId: Long, repairInfoDto: RepairInfoDto): Long {
        val carRepairState = CarRepairStateDto()
        val repairProcessDto = storageFeignClient.getRepairProcessById(repairProcessId)

        carRepairState.carId = repairProcessDto.carId
        carRepairState.engineerId = repairInfoDto.engineerId
        carRepairState.repairParts!!.addAll(repairPartsService.install(repairInfoDto.repairParts, repairProcessDto.carId!!))
        carRepairState.application = repairInfoDto.application
        carRepairState.mechanicIds = repairInfoDto.mechanicIds
        carRepairState.repairState = RepairState.DONE
        carRepairState.stateChangeTime = LocalDateTime.now()
        carRepairState.repairProblems = repairInfoDto.repairProblems
        LOGGER.info("State окончания работ. Начинаем сохранение... $carRepairState")
        return storageFeignClient.saveCarRepairState(carRepairState)
    }
}























