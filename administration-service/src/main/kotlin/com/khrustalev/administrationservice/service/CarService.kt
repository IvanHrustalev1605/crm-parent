package com.khrustalev.administrationservice.service

import com.khrustalev.administrationservice.dto.CarDto

interface CarService {
    fun getCarById(id: Long) : CarDto?
    fun getCarByNumber(number: String) : CarDto?
    fun getCarByVinNumber(vinNumber: String) : CarDto?
    fun saveCar(carDto: CarDto) : CarDto?
    fun deleteCarByNumberOrVin(carNumber: String) : Boolean?
    fun getAllCars() : MutableList<CarDto>
    fun getCarsInRepair() : MutableList<CarDto>
    fun getCarsInBase() : MutableList<CarDto>
}
