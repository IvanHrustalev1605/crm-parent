package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.service.abstracts.DriverService
import com.khrustalev.storageservice.service.abstracts.GenerateValueService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/storage/test-data")
class GenerateTestDataController(private val generateValueService: GenerateValueService
) {
    @GetMapping("/generate")
    fun generate() : ResponseEntity<Any> {
        return ResponseEntity(generateValueService.generateRepairParts(), HttpStatus.CREATED)
    }
    @GetMapping("/dictionary/save")
    fun save() : ResponseEntity<Boolean> {
        return ResponseEntity(generateValueService.generateDictionary(), HttpStatus.CREATED)
    }
}