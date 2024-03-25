package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.CarArrivalStateDto
import com.khrustalev.storageservice.dto.CarDto
import com.khrustalev.storageservice.dto.RepairDto
import com.khrustalev.storageservice.service.abstracts.CarService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
    @GetMapping("/all")
    fun getAllCars() : ResponseEntity<MutableList<CarDto>> {
        return ResponseEntity(carService.getAllCars(), HttpStatus.OK)
    }
    @GetMapping("/by-number")
    fun getCarByNumber(@RequestParam carNumber: String) : ResponseEntity<CarDto> {
        return ResponseEntity(carService.getCarByNumber(carNumber), HttpStatus.OK)
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
    @GetMapping("/repairs")
    fun getRepairsByCarId(@RequestParam carId: Long, @RequestParam(required = false) actual: Boolean) : ResponseEntity<MutableList<RepairDto>> {
        return ResponseEntity(carService.getRepairsByCarId(carId, actual), HttpStatus.OK)
    }
    @GetMapping("/all-arrives")
    fun getAllArrives(@RequestParam carId: Long): ResponseEntity<MutableList<CarArrivalStateDto>> {
        return ResponseEntity(carService.getAllArrivesByCarId(carId), HttpStatus.OK)
    }
}
