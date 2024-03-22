package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.CarDto
import com.khrustalev.storageservice.dto.RepairDto
import com.khrustalev.storageservice.entity.schems.storage.Car

interface CarService {
    fun findById(carId: Long): Car?
    fun findCarIdByCarNumber(carNumber: String): Long
    fun findByVin(vin: String): CarDto
    fun mapFromEntityToDto(car: Car?) : CarDto?
    fun getCarsInRepair() : MutableList<CarDto>
    fun getAllCars() : MutableList<CarDto>
    fun deleteCarByNumberOrVin(v: String) : Boolean
    fun saveCar(carDto: CarDto) : CarDto
    fun getCarByNumber(number: String) : CarDto
    fun getCarsInBase() : MutableList<CarDto>

    fun getRepairsByCarId(carId: Long, actual: Boolean) : MutableList<RepairDto>
}
