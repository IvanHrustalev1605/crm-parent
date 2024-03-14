package com.khrustalev.administrationservice.controllers

import com.khrustalev.administrationservice.dto.CarDto
import com.khrustalev.administrationservice.service.CarService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/rest/cars")
class CarController(private val carService: CarService) {
    @GetMapping("/by-car-number")
    fun getCarByNumber(@RequestParam carNumber: String) : ResponseEntity<CarDto> {
        return ResponseEntity(carService.getCarByNumber(carNumber), HttpStatus.OK)
    }
    @GetMapping("/by-car-id")
    fun getCarById(@RequestParam id: Long) : ResponseEntity<CarDto> {
        return ResponseEntity(carService.getCarById(id), HttpStatus.OK)
    }
    @GetMapping("/by-car-vin")
    fun getCarByVin(@RequestParam vinNumber: String) : ResponseEntity<CarDto> {
        return ResponseEntity(carService.getCarByVinNumber(vinNumber), HttpStatus.OK)
    }
    @GetMapping("/all")
    fun getAllCars() : ResponseEntity<MutableList<CarDto>> {
        return ResponseEntity(carService.getAllCars(), HttpStatus.OK)
    }
    @GetMapping("/in-repair")
    fun getCarsInRepair() : ResponseEntity<MutableList<CarDto>> {
        return ResponseEntity(carService.getCarsInRepair(), HttpStatus.OK)
    }
    @GetMapping("/in-base")
    fun getCarsInBase() : ResponseEntity<MutableList<CarDto>> {
        return ResponseEntity(carService.getCarsInBase(), HttpStatus.OK)
    }
    @PostMapping("/save")
    fun saveCar(@RequestBody carDto: CarDto) : ResponseEntity<CarDto> {
        return ResponseEntity(carService.saveCar(carDto), HttpStatus.CREATED)
    }
    @DeleteMapping("/delete")
    fun deleteCarByNumberOrVin(@RequestParam v: String) : ResponseEntity<Boolean> {
        return ResponseEntity(carService.deleteCarByNumberOrVin(v), HttpStatus.OK)
    }
}