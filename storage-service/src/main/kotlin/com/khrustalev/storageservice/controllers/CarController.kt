package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.CarDto
import com.khrustalev.storageservice.service.abstracts.CarService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/storage/car")
class CarController(private val carService: CarService) {

    @GetMapping("/find-by-number")
    fun getCarIdByNumber(@RequestParam("carNumber") carNumber: String): ResponseEntity<Long> {
        return ResponseEntity(carService.findCarIdByCarNumber(carNumber), HttpStatus.OK)
    }
    @GetMapping("/find-by-vin")
    fun getByVin(@RequestParam vin: String) : ResponseEntity<CarDto> {
        return ResponseEntity(carService.findByVin(vin), HttpStatus.OK)
    }
    @GetMapping("/by-id")
    fun getCarById(@RequestParam carId: Long) : ResponseEntity<CarDto> {
        return ResponseEntity(carService.mapFromEntityToDto(carService.findById(carId)), HttpStatus.OK)
    }
}
