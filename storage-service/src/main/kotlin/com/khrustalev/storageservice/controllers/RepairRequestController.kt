package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.RepairRequestDto
import com.khrustalev.storageservice.service.abstracts.RepairRequestService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/storage/repairRequest")
class RepairRequestController(private val repairRequestService: RepairRequestService) {

    @GetMapping("/find-all-by-car-number")
    fun getAllRepairRequestByCarNumber(@RequestParam("carNumber") carNumber: String) : ResponseEntity<MutableList<RepairRequestDto>?> {
        return ResponseEntity(repairRequestService.getAllRepairRequestByCarNumber(carNumber), HttpStatus.OK)
    }
    @PostMapping("/save")
    fun save(@RequestBody repairRequestDto: RepairRequestDto) : ResponseEntity<Boolean> {
        return ResponseEntity(repairRequestService.createRepairRequest(repairRequestDto), HttpStatus.OK)
    }
}