package com.khrustalev.administrationservice.service.impl

import com.khrustalev.administrationservice.dto.CarDto
import com.khrustalev.administrationservice.dto.RepairDto
import com.khrustalev.administrationservice.feign.StorageFeignClient
import com.khrustalev.administrationservice.service.CarService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CarServiceImpl(private val storageFeignClient: StorageFeignClient) : CarService {
    private val LOGGER: Logger = LoggerFactory.getLogger(CarServiceImpl::class.java)

    override fun getCarById(id: Long): CarDto? {
        val response = storageFeignClient.getCarById(id)
        if (response.statusCode.is2xxSuccessful) {
            return response.body
        }
        LOGGER.info("No car found by ID: $id!")
        return null
    }

    override fun getCarByNumber(number: String): CarDto? {
        val response = storageFeignClient.getCarByNumber(number)
        if (response.statusCode.is2xxSuccessful) {
            return response.body
        }
        LOGGER.info("No car found by CarNumber: $number!")
        return null
    }

    override fun getCarByVinNumber(vinNumber: String): CarDto? {
        val response = storageFeignClient.getByVin(vinNumber)
        if (response.statusCode.is2xxSuccessful) {
            return response.body
        }
        LOGGER.info("No car found by Vin: $vinNumber!")
        return null
    }

    override fun saveCar(carDto: CarDto): CarDto? {
        val response = storageFeignClient.saveCar(carDto)
        if (response.statusCode.is2xxSuccessful) {
            return response.body
        }
        LOGGER.info("Exception while saving car: ${carDto.number}!")
        return null
    }

    override fun deleteCarByNumberOrVin(v: String): Boolean? {
        val response = storageFeignClient.deleteCarByNumberOrVin(v)
        if (response.statusCode.is2xxSuccessful) {
            return response.body!!
        }
        LOGGER.info("Exception while delete car with carNumber: $v or vin number: $v !")
        return null
    }

    override fun getAllCars(): MutableList<CarDto> {
        val response = storageFeignClient.getAllCars()
        if (response.statusCode.is2xxSuccessful) {
            return response.body!!
        }
        LOGGER.info("Car list is empty")
        return mutableListOf()
    }

    override fun getCarsInRepair(): MutableList<CarDto> {
        val response = storageFeignClient.getCarsInRepair()
        if (response.statusCode.is2xxSuccessful) {
            return response.body!!
        }
        LOGGER.info("Car list is empty")
        return mutableListOf()
    }

    override fun getCarsInBase(): MutableList<CarDto> {
        val response = storageFeignClient.getCarsInBase()
        if (response.statusCode.is2xxSuccessful) {
            return response.body!!
        }
        LOGGER.info("Car list is empty")
        return mutableListOf()
    }

    override fun getRepairsByCarNumber(carId: Long, actual: Boolean): MutableList<RepairDto> {
        val responseEntity = storageFeignClient.getRepairsByCarId(carId, actual)
        if (responseEntity.statusCode.is2xxSuccessful) {
            return responseEntity.body!!
        }
        LOGGER.error("${responseEntity.statusCode}")
        LOGGER.info("Repairs for car with ID: $carId is empty!")
        return mutableListOf()
    }
}