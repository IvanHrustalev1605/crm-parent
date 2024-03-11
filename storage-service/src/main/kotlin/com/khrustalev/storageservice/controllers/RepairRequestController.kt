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
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/storage/repairRequest")
class RepairRequestController(private val repairRequestService: RepairRequestService) {
    @GetMapping("/by-id")
    fun findById(@RequestParam id: Long) : ResponseEntity<RepairRequestDto> {
        return ResponseEntity(repairRequestService.getById(id), HttpStatus.OK)
    }

    @GetMapping("/find-all-by-car-number")
    fun getAllRepairRequestByCarNumber(@RequestParam("carNumber") carNumber: String) : ResponseEntity<MutableList<RepairRequestDto>?> {
        return ResponseEntity(repairRequestService.getAllRepairRequestByCarNumber(carNumber), HttpStatus.OK)
    }
    @PostMapping("/save")
    fun save(@RequestBody repairRequestDto: RepairRequestDto) : ResponseEntity<Boolean> {
        return ResponseEntity(repairRequestService.createRepairRequest(repairRequestDto), HttpStatus.OK)
    }
    @GetMapping("/actual-repair-request/by-car-number")
    fun getActualRepairRequestForRepairProcess(@RequestParam("actualDate") actualDate: LocalDateTime,
                                               @RequestParam("carNumber") carNumber: String) : ResponseEntity<MutableList<Long>> {
        return ResponseEntity(repairRequestService.getActualForRepairProcess(actualDate, carNumber), HttpStatus.OK)
    }
    @PostMapping("/agreed")
    fun agreedRequest(@RequestParam("repairRequestId") id: Long) : ResponseEntity<RepairRequestDto?> {
        return ResponseEntity(repairRequestService.takeAgreed(id), HttpStatus.CREATED)
    }
    @GetMapping("/find-by-number")
    fun getByRequestNumber(@RequestParam("repairRequestNumber") number: Long) : ResponseEntity<RepairRequestDto> {
        return ResponseEntity(repairRequestService.getByRepairRequestNumber(number), HttpStatus.OK)
    }
}