package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.CarRepairStateDto
import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.enums.RepairState
import com.khrustalev.repairservice.feign.RepairPartsServiceFeignClient
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.CarRepairStateService
import com.khrustalev.repairservice.service.RepairBoxService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import java.time.LocalDateTime
import java.util.Objects

@Service
class CarRepairStateServiceImpl(private val storageFeignClient: StorageFeignClient,
                                private val repairPartsServiceFeignClient: RepairPartsServiceFeignClient,
                                private val boxService: RepairBoxService) : CarRepairStateService {
    private val LOGGER: Logger = LoggerFactory.getLogger(CarRepairStateServiceImpl::class.java)

    override fun createNewRepairState(repairInfoDto: RepairInfoDto): Long {
        val carRepairState = CarRepairStateDto()
            carRepairState.repairBoxId = checkRepairBox(repairInfoDto.repairBoxNumber)
            carRepairState.carId = repairInfoDto.carId
            carRepairState.engineerId = repairInfoDto.engineerId
            carRepairState.createdAt = LocalDateTime.now()
            carRepairState.repairState = RepairState.NEW
            carRepairState.application = repairInfoDto.application
            carRepairState.mechanicIds = repairInfoDto.mechanicIds
            carRepairState.repairProblems = repairInfoDto.repairProblems
            LOGGER.info("Новая state создана. Начинаем сохранение... $carRepairState")
            return storageFeignClient.saveCarRepairState(carRepairState)
    }

    override fun changeRepairState(repairInfoDto: RepairInfoDto): Long {
        val previousRepairStateByCarId = storageFeignClient.getPreviousRepairStateByCarId(repairInfoDto.carId!!)

        val carRepairState = CarRepairStateDto()

        carRepairState.carRepairStateParentId = previousRepairStateByCarId.id
        carRepairState.repairBoxId = if (Objects.isNull(repairInfoDto.repairBoxNumber) || repairInfoDto.repairBoxNumber == 0 || previousRepairStateByCarId.repairBoxId == storageFeignClient.getBoxByNumber(repairInfoDto.repairBoxNumber).body!!.id)
            previousRepairStateByCarId.repairBoxId else {
            boxService.setBoxFree(storageFeignClient.getBoxById(previousRepairStateByCarId.repairBoxId!!).body!!.boxNumber!!)
            checkRepairBox(repairInfoDto.repairBoxNumber)
            }
        carRepairState.repairState = if (Objects.isNull(repairInfoDto.repairStateNumber)) previousRepairStateByCarId.repairState!! else RepairState.entries[repairInfoDto.repairStateNumber]
        carRepairState.repairProblems = repairInfoDto.repairProblems
        carRepairState.createdAt = LocalDateTime.now()
        carRepairState.application = repairInfoDto.application

        carRepairState.mechanicIds = if (CollectionUtils.isEmpty(repairInfoDto.mechanicIds) || repairInfoDto.mechanicIds!!.contains(0) || repairInfoDto.mechanicIds.containsAll(previousRepairStateByCarId.mechanicIds!!))
            previousRepairStateByCarId.mechanicIds else repairInfoDto.mechanicIds

        carRepairState.engineerId = if (Objects.isNull(repairInfoDto.engineerId) || repairInfoDto.engineerId == 0L || repairInfoDto.engineerId!! == previousRepairStateByCarId.engineerId!!)
            previousRepairStateByCarId.engineerId else repairInfoDto.engineerId

        if (!CollectionUtils.isEmpty(repairInfoDto.repairPartsNumbers)) {
            carRepairState.repairParts.addAll(repairPartsServiceFeignClient.installPartToCar(repairInfoDto.repairPartsNumbers, repairInfoDto.carId))
        } else carRepairState.repairParts = mutableListOf()
        carRepairState.carId = previousRepairStateByCarId.carId!!
        LOGGER.info("State изменения информации о ремонте создана. Начинаем сохранение... $carRepairState")
        return storageFeignClient.saveCarRepairState(carRepairState)
    }

    override fun createEndRepairState(repairProcessId: Long, repairInfoDto: RepairInfoDto): Long {
        val carRepairState = CarRepairStateDto()
        val previousRepairStateByCarId = storageFeignClient.getPreviousRepairStateByCarId(repairInfoDto.carId!!)

        val boxNumber = storageFeignClient.getBoxById(previousRepairStateByCarId.repairBoxId!!).body!!.boxNumber!!
        if (boxService.setBoxFree(boxNumber)) {
            carRepairState.repairBoxId = previousRepairStateByCarId.repairBoxId!!
        } else throw Exception("Не удалось освободить бокс!")

        carRepairState.carRepairStateParentId = previousRepairStateByCarId.id
        carRepairState.carId = previousRepairStateByCarId.carId!!

        carRepairState.engineerId = if (Objects.isNull(repairInfoDto.engineerId) || repairInfoDto.engineerId == 0L || repairInfoDto.engineerId!! == previousRepairStateByCarId.engineerId!!)
            previousRepairStateByCarId.engineerId else repairInfoDto.engineerId

        if (!CollectionUtils.isEmpty(repairInfoDto.repairPartsNumbers)) {
            carRepairState.repairParts.addAll(repairPartsServiceFeignClient.installPartToCar(repairInfoDto.repairPartsNumbers, repairInfoDto.carId))
        } else carRepairState.repairParts = mutableListOf()

        carRepairState.application = repairInfoDto.application

        carRepairState.mechanicIds = if (CollectionUtils.isEmpty(repairInfoDto.mechanicIds) || repairInfoDto.mechanicIds!!.containsAll(previousRepairStateByCarId.mechanicIds!!))
            previousRepairStateByCarId.mechanicIds else repairInfoDto.mechanicIds

        carRepairState.repairState = RepairState.DONE
        carRepairState.createdAt = LocalDateTime.now()
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























