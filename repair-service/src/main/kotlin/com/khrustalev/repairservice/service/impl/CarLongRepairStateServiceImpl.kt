package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.CarLongRepairStateDto
import com.khrustalev.repairservice.dto.RepairInfoDto
import com.khrustalev.repairservice.dto.RepairProcessDto
import com.khrustalev.repairservice.feign.RepairPartsServiceFeignClient
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.CarLongRepairStateService
import com.khrustalev.repairservice.service.RepairBoxService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import java.time.LocalDateTime

@Service
class CarLongRepairStateServiceImpl(private val storageFeignClient: StorageFeignClient,
                                    private val repairPartsServiceFeignClient: RepairPartsServiceFeignClient,
                                    private val boxService: RepairBoxService) : CarLongRepairStateService {
    private val logger: Logger = LoggerFactory.getLogger(CarLongRepairStateService::class.java)

    override fun createNewLongRepairState(repairInfoDto: RepairInfoDto, repairProcessDto: RepairProcessDto?): Long {
        val carLongRepairStateDto = CarLongRepairStateDto()
        carLongRepairStateDto.carId = repairInfoDto.carId
        carLongRepairStateDto.createdAt = LocalDateTime.now()
        carLongRepairStateDto.repairProblems = repairInfoDto.repairProblems
        if (repairProcessDto != null) {
            if (repairInfoDto.repairBoxNumber == null || repairInfoDto.repairBoxNumber == 0) {
                carLongRepairStateDto.boxIds.add(storageFeignClient.getPreviousRepairStateByCarId(repairInfoDto.carId!!).repairBoxId!!)
            } else {
                val repairBoxDto = boxService.getBoxByNumber(repairInfoDto.repairBoxNumber).takeIf { it.isFree }
                    ?: throw Exception("Бокс занят. Создание процесса ремонта не возможно.")
                carLongRepairStateDto.boxIds.add(repairBoxDto.id!!)
            }
        } else {
            val repairBoxDto = boxService.getBoxByNumber(repairInfoDto.repairBoxNumber).takeIf { it.isFree }
                ?: throw Exception("Бокс занят. Создание процесса ремонта не возможно.")
            carLongRepairStateDto.boxIds.add(repairBoxDto.id!!)
        }
        carLongRepairStateDto.mechanicIds.addAll(repairInfoDto.mechanicIds!!)
        carLongRepairStateDto.engineerId = repairInfoDto.engineerId
        carLongRepairStateDto.longRepairStatesId = 1
        carLongRepairStateDto.endAt = null
        carLongRepairStateDto.carStayInBase = repairInfoDto.carStayInBase
        carLongRepairStateDto.repairPartIds = mutableListOf()
        carLongRepairStateDto.carLongRepairStateParentId = null

        val result = storageFeignClient.saveCarLongRepairState(carLongRepairStateDto)
        if (result != null) {
            return result.id
        }
        logger.info("При сохранение LongRepairState произошла ошибка")
        return -1
    }

    override fun updateLongRepairState(repairInfoDto: RepairInfoDto): Long {
        val previousLongRepairState = storageFeignClient.getPreviousLongRepairStateByCarId(repairInfoDto.carId!!)
        if (previousLongRepairState != null) {
            previousLongRepairState.endAt = LocalDateTime.now()
            if (storageFeignClient.saveCarLongRepairState(previousLongRepairState) != null) {
                val carLongRepairStateDto = CarLongRepairStateDto()
                if (repairInfoDto.repairBoxNumber != null && repairInfoDto.repairBoxNumber != 0) {
                    val repairBoxDto = boxService.getBoxByNumber(repairInfoDto.repairBoxNumber).takeIf { it.isFree }
                        ?: throw Exception("Бокс занят. Создание стадии ремонта не возможно.")
                    carLongRepairStateDto.boxIds.add(repairBoxDto.id!!)
                } else {
                    carLongRepairStateDto.boxIds.addAll(previousLongRepairState.boxIds)
                }
                carLongRepairStateDto.createdAt = LocalDateTime.now()
                carLongRepairStateDto.carStayInBase = repairInfoDto.carStayInBase
                carLongRepairStateDto.carLongRepairStateParentId = previousLongRepairState.id
                carLongRepairStateDto.longRepairStatesId = repairInfoDto.longRepairStateId
                carLongRepairStateDto.repairProblems = repairInfoDto.repairProblems.ifEmpty { previousLongRepairState.repairProblems }
                if (repairInfoDto.repairBoxNumber == null && repairInfoDto.repairBoxNumber == 0) carLongRepairStateDto.boxIds.addAll(previousLongRepairState.boxIds) else carLongRepairStateDto.boxIds.add(repairInfoDto.repairBoxNumber.toLong())
                if (CollectionUtils.isEmpty(repairInfoDto.mechanicIds) || repairInfoDto.mechanicIds!!.contains(0)) carLongRepairStateDto.mechanicIds.addAll(previousLongRepairState.mechanicIds) else carLongRepairStateDto.mechanicIds.addAll(repairInfoDto.mechanicIds!!)
                if (repairInfoDto.engineerId == null || repairInfoDto.engineerId == 0L) carLongRepairStateDto.engineerId = previousLongRepairState.engineerId else carLongRepairStateDto.engineerId = repairInfoDto.engineerId
                carLongRepairStateDto.carId = previousLongRepairState.carId
                carLongRepairStateDto.endAt = null
                if (!CollectionUtils.isEmpty(repairInfoDto.repairPartsNumbers)) {
                    carLongRepairStateDto.repairPartIds.addAll(repairPartsServiceFeignClient.installPartToCar(repairInfoDto.repairPartsNumbers, repairInfoDto.carId))
                } else carLongRepairStateDto.repairPartIds = mutableListOf()
                val result = storageFeignClient.saveCarLongRepairState(carLongRepairStateDto)
                if (result != null) {
                    logger.info("Успешно добавили longRepairState для машины с ID: ${repairInfoDto.carId}")
                    return result.id
                }
                return -1
            } else {
                throw Exception("Не удалось обновить предыдущую state")
            }
        } else {
            throw Exception("Не удалось найти предыдущую state длительного ремонта для машины с ID: ${repairInfoDto.carId}")
        }

    }

    override fun createEndLongRepairState(repairInfoDto: RepairInfoDto): Long {
        val previousLongRepairState = storageFeignClient.getPreviousLongRepairStateByCarId(repairInfoDto.carId!!)
        if (previousLongRepairState != null) {
            previousLongRepairState.endAt = LocalDateTime.now()
            if (storageFeignClient.saveCarLongRepairState(previousLongRepairState) != null) {
                val carLongRepairStateDto = CarLongRepairStateDto()
                if (repairInfoDto.repairBoxNumber != null && repairInfoDto.repairBoxNumber != 0 && !previousLongRepairState.boxIds.contains(repairInfoDto.repairBoxNumber.toLong())) {
                    val repairBoxDto = boxService.getBoxByNumber(repairInfoDto.repairBoxNumber).takeIf { it.isFree }
                        ?: throw Exception("Бокс занят. Создание стадии ремонта не возможно.")
                    carLongRepairStateDto.boxIds.add(repairBoxDto.id!!)
                } else {
                    carLongRepairStateDto.boxIds.addAll(previousLongRepairState.boxIds)
                }
                carLongRepairStateDto.createdAt = LocalDateTime.now()
                carLongRepairStateDto.carStayInBase = repairInfoDto.carStayInBase
                carLongRepairStateDto.carLongRepairStateParentId = previousLongRepairState.id
                carLongRepairStateDto.longRepairStatesId = 5
                carLongRepairStateDto.repairProblems = repairInfoDto.repairProblems.ifEmpty { previousLongRepairState.repairProblems }
                if (repairInfoDto.repairBoxNumber == null && repairInfoDto.repairBoxNumber == 0) carLongRepairStateDto.boxIds.addAll(previousLongRepairState.boxIds) else carLongRepairStateDto.boxIds.add(repairInfoDto.repairBoxNumber.toLong())
                if (CollectionUtils.isEmpty(repairInfoDto.mechanicIds) || repairInfoDto.mechanicIds!!.contains(0)) carLongRepairStateDto.mechanicIds.addAll(previousLongRepairState.mechanicIds) else carLongRepairStateDto.mechanicIds.addAll(repairInfoDto.mechanicIds!!)
                if (repairInfoDto.engineerId == null || repairInfoDto.engineerId == 0L) carLongRepairStateDto.engineerId = previousLongRepairState.engineerId else carLongRepairStateDto.engineerId = repairInfoDto.engineerId
                carLongRepairStateDto.carId = previousLongRepairState.carId
                carLongRepairStateDto.endAt = LocalDateTime.now()
                if (!CollectionUtils.isEmpty(repairInfoDto.repairPartsNumbers)) {
                    carLongRepairStateDto.repairPartIds.addAll(repairPartsServiceFeignClient.installPartToCar(repairInfoDto.repairPartsNumbers, repairInfoDto.carId))
                } else carLongRepairStateDto.repairPartIds = mutableListOf()
                val result = storageFeignClient.saveCarLongRepairState(carLongRepairStateDto)
                if (result != null) {
                    logger.info("Успешно добавили longRepairState для машины с ID: ${repairInfoDto.carId}")
                    return result.id
                }
                return -1
            } else {
                throw Exception("Не удалось обновить предыдущую state")
            }
        } else {
            throw Exception("Не удалось найти предыдущую state длительного ремонта для машины с ID: ${repairInfoDto.carId}")
        }
    }

}