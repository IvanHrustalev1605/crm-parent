package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.service.abstracts.DriverService
import com.khrustalev.storageservice.service.abstracts.GenerateValueService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test-data")
class GenerateTestDataController(private val generateValueService: GenerateValueService
) {
    @GetMapping
    fun generate() : ResponseEntity<Boolean> {
        return ResponseEntity(generateValueService.generateDbValues(), HttpStatus.CREATED)
    }
}