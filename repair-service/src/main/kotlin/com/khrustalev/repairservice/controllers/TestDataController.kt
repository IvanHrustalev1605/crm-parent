package com.khrustalev.repairservice.controllers

import com.khrustalev.repairservice.service.TestDataService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/generate-test-data")
class TestDataController(private val testDataService: TestDataService) {

    @GetMapping
    fun generateDate() : ResponseEntity<Boolean> {
        return ResponseEntity(testDataService.generateTestData(), HttpStatus.OK)
    }
}