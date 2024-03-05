package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.DriverDto
import com.khrustalev.storageservice.service.abstracts.DriverService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/storage/driver")
class DriverController(private val driverService: DriverService) {
    @GetMapping("/find-by-car-id")
    fun getByCarId(@RequestParam carId : Long) : ResponseEntity<DriverDto> {
        return ResponseEntity(driverService.findByCarId(carId), HttpStatus.OK)
    }
}