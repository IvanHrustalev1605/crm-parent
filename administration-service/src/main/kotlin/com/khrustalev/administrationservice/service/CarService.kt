package com.khrustalev.administrationservice.service

import com.khrustalev.administrationservice.dto.CarDto
import com.khrustalev.administrationservice.dto.RepairDto

interface CarService {
    fun getCarById(id: Long) : CarDto?
    fun getCarByNumber(number: String) : CarDto?
    fun getCarByVinNumber(vinNumber: String) : CarDto?
    fun saveCar(carDto: CarDto) : CarDto?
    fun deleteCarByNumberOrVin(carNumber: String) : Boolean?
    fun getAllCars() : MutableList<CarDto>
    fun getCarsInRepair() : MutableList<CarDto>
    fun getCarsInBase() : MutableList<CarDto>
    fun getRepairsByCarNumber(carId: Long, actual: Boolean) : MutableList<RepairDto>
}
