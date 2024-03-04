package com.khrustalev.repairservice.controllers

import com.khrustalev.repairservice.service.TestDataService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/generate-test-data")
class TestDataController(private val testDataService: TestDataService) {

    @Operation(summary = "Сгенерировать тестовые данные в БД")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Успешно!"),
        ApiResponse(responseCode = "500", description = "Ошибка!")
    ])
    @GetMapping
    fun generateDate() : ResponseEntity<Boolean> {
        return ResponseEntity(testDataService.generateTestData(), HttpStatus.OK)
    }
}