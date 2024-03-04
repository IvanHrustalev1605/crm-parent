package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.CarArrivalStateDto
import com.khrustalev.storageservice.dto.CarRepairStateDto
import com.khrustalev.storageservice.service.abstracts.CarStateService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/storage/carState")
class CarStateController(private val carStateService: CarStateService) {
    @PostMapping("/arrival/save")
    fun saveArrivalCarState(@RequestBody(required = true) carArrivalStateDto: CarArrivalStateDto) : ResponseEntity<Boolean> {
        return ResponseEntity(carStateService.saveArrivalState(carArrivalStateDto), HttpStatus.CREATED)
    }
    @PostMapping("/repair/save")
    fun saveRepairCarState(@RequestBody(required = true) carRepairStateDto: CarRepairStateDto) : ResponseEntity<Boolean> {
        return ResponseEntity(carStateService.saveRepairState(carRepairStateDto), HttpStatus.CREATED)
    }
    @GetMapping("/arrival/get-by-carNumber")
    fun getLastCarArrivalStateByCarNumber(@RequestParam("carNumber") carNumber: String) : ResponseEntity<CarArrivalStateDto> {
        return ResponseEntity(carStateService.getCarArrivalStateByCarNumber(carNumber), HttpStatus.OK)
    }
}