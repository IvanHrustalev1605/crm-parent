package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.CarRepairStateDto
import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.enums.RepairState
import com.khrustalev.repairservice.exceptions.SomethingGoWrongException
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.CarRepairStateService
import com.khrustalev.repairservice.service.RepairBoxService
import com.khrustalev.repairservice.service.RepairPartsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.math.E

@Service
class CarRepairStateServiceImpl(private val storageFeignClient: StorageFeignClient,
                                private val repairPartsService: RepairPartsService,
                                private val boxService: RepairBoxService) : CarRepairStateService {
    private val LOGGER: Logger = LoggerFactory.getLogger(CarRepairStateServiceImpl::class.java)

    override fun createNewRepairState(repairInfoDto: RepairInfoDto): Long {
        val carRepairState = CarRepairStateDto()
            carRepairState.repairBoxId = checkRepairBox(repairInfoDto.repairBoxNumber)
            carRepairState.carId = repairInfoDto.carId
            carRepairState.engineerId = repairInfoDto.engineerId
            carRepairState.stateChangeTime = LocalDateTime.now()
            carRepairState.repairState = RepairState.NEW
            carRepairState.application = repairInfoDto.application
            carRepairState.mechanicIds = repairInfoDto.mechanicIds
            carRepairState.repairProblems = repairInfoDto.repairProblems
            LOGGER.info("Новая state создана. Начинаем сохранение... $carRepairState")
            return storageFeignClient.saveCarRepairState(carRepairState)
    }

    override fun changeRepairState(repairInfoDto: RepairInfoDto): Long {
        val carRepairState = CarRepairStateDto()
        carRepairState.repairBoxId = checkRepairBox(repairInfoDto.repairBoxNumber)
        carRepairState.repairState = RepairState.entries[repairInfoDto.repairStateNumber]
        carRepairState.repairProblems = repairInfoDto.repairProblems
        carRepairState.stateChangeTime = LocalDateTime.now()
        carRepairState.application = repairInfoDto.application
        carRepairState.mechanicIds = repairInfoDto.mechanicIds
        carRepairState.engineerId = repairInfoDto.engineerId
//        carRepairState.repairParts.addAll(repairPartsService.install(repairInfoDto.repairParts, repairInfoDto.carId!!))
        carRepairState.carId = repairInfoDto.carId
        LOGGER.info("State изменения информации о ремонте создана. Начинаем сохранение... $carRepairState")
        return storageFeignClient.saveCarRepairState(carRepairState)
    }

    override fun createEndRepairState(repairProcessId: Long, repairInfoDto: RepairInfoDto): Long {
        val carRepairState = CarRepairStateDto()
        val repairProcessDto = storageFeignClient.getRepairProcessById(repairProcessId)
        if (boxService.setBoxFree(repairInfoDto.repairBoxNumber)) {
            carRepairState.repairBoxId = boxService.getBoxByNumber(repairInfoDto.repairBoxNumber).id
        } else throw Exception("Не удалось освободить бокс!")
        carRepairState.carId = repairProcessDto.carId
        carRepairState.engineerId = repairInfoDto.engineerId
//        carRepairState.repairParts!!.addAll(repairPartsService.install(repairInfoDto.repairParts, repairProcessDto.carId!!))
        carRepairState.application = repairInfoDto.application
        carRepairState.mechanicIds = repairInfoDto.mechanicIds
        carRepairState.repairState = RepairState.DONE
        carRepairState.stateChangeTime = LocalDateTime.now()
        carRepairState.repairProblems = repairInfoDto.repairProblems
        LOGGER.info("State окончания работ. Начинаем сохранение... $carRepairState")
        return storageFeignClient.saveCarRepairState(carRepairState)
    }
    private fun checkRepairBox(repairBoxNumber: Int) : Long {
        if (boxService.busyBox(repairBoxNumber)) {
            return boxService.getBoxByNumber(repairBoxNumber).id!!
        } else throw Exception("Бокс пока что занят. Нужно подождать")
    }
}























