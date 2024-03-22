package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.CarDto
import com.khrustalev.storageservice.dto.RepairDto
import com.khrustalev.storageservice.entity.schems.storage.Car
import com.khrustalev.storageservice.exception.NotFoundEntityException
import com.khrustalev.storageservice.mappers.CarMapper
import com.khrustalev.storageservice.mappers.RepairMapper
import com.khrustalev.storageservice.repository.CarRepository
import com.khrustalev.storageservice.service.abstracts.CarService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import java.util.Objects

@Service
class CarServiceImpl(private val carRepository: CarRepository,
                     private val carMapper: CarMapper,
                     @Lazy private val repairMapper: RepairMapper) : CarService {
    override fun findById(carId: Long): Car? {
        return carRepository.findById(carId).orElseThrow { NotFoundEntityException("Car not found by id $carId") }
    }

    override fun findCarIdByCarNumber(carNumber: String): Long {
        return carRepository.customFindCarIdByNumber(carNumber)
    }

    override fun findByVin(vin: String): CarDto {
        return carMapper.toDto(carRepository.findByVinNumber(vin).orElseThrow { NotFoundEntityException("Car by VIN: $vin not found!") })
    }

    override fun mapFromEntityToDto(car: Car?): CarDto? {
        return carMapper.toDto(car!!)
    }

    override fun getCarsInRepair(): MutableList<CarDto> {
        return carRepository.getCarInRepair().stream()
            .map { carMapper.toDto(it) }
            .toList().toMutableList()
    }

    override fun getAllCars(): MutableList<CarDto> {
        return carRepository.findAll().stream()
            .map { carMapper.toDto(it) }
            .toList()
            .toMutableList()
    }

    override fun deleteCarByNumberOrVin(v: String): Boolean {
        return if (v.length < 10) {
            carRepository.deleteByNumber(v)
            true
        } else if (v.length  == 17) {
            carRepository.deleteByVinNumber(v)
            true
        } else return false
    }

    override fun saveCar(carDto: CarDto): CarDto {
       return carMapper.toDto(carRepository.save(carMapper.toEntity(carDto)))
    }

    override fun getCarByNumber(number: String): CarDto {
        return carMapper.toDto(carRepository.findByNumber(number).orElseThrow { Exception("") })
    }

    override fun getCarsInBase(): MutableList<CarDto> {
        return carRepository.getCarsInBase().stream()
            .map { carMapper.toDto(it) }
            .toList()
            .toMutableList()
    }

    override fun getRepairsByCarId(carId: Long, actual: Boolean): MutableList<RepairDto> {
        if (Objects.nonNull(actual) && actual) {
            return carRepository.getActualCarRepairs(carId).map { repairMapper.toDto(it) }.toMutableList()
        }
        return carRepository.getAllCarRepairs(carId).map { repairMapper.toDto(it) }.toMutableList()
    }
}