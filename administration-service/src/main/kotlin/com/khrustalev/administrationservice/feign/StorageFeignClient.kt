package com.khrustalev.administrationservice.feign

import com.khrustalev.administrationservice.dto.CarDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@FeignClient(name = "storage-feign-client", url = "http://localhost:8888/api/storage")
interface StorageFeignClient {
    /*Cars*/
    @GetMapping("/car/find-by-vin")
    fun getByVin(@RequestParam vin: String) : ResponseEntity<CarDto>
    @GetMapping("/car/by-id")
    fun getCarById(@RequestParam carId: Long) : ResponseEntity<CarDto>
    @GetMapping("/car/all")
    fun getAllCars() : ResponseEntity<MutableList<CarDto>>
    @GetMapping("/car/by-number")
    fun getCarByNumber(@RequestParam carNumber: String) : ResponseEntity<CarDto>
    @GetMapping("/car/in-repair")
    fun getCarsInRepair() : ResponseEntity<MutableList<CarDto>>
    @GetMapping("/car/in-base")
    fun getCarsInBase() : ResponseEntity<MutableList<CarDto>>
    @PostMapping("/car/save")
    fun saveCar(@RequestBody carDto: CarDto) : ResponseEntity<CarDto>
    @DeleteMapping("/car/delete")
    fun deleteCarByNumberOrVin(@RequestParam v: String) : ResponseEntity<Boolean>
    /*-----*/
    /*Engineers*/

    /*-----*/
    /*RepairRequests*/

    /*-----*/
    /*Repair*/

    /*-----*/
    /*Security*/

    /*-----*/
    /*Mechanics*/

    /*-----*/
    /*Drivers*/

    /*-----*/

}