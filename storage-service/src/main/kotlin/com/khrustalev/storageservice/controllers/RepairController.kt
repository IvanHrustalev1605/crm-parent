package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.CarRepairStateDto
import com.khrustalev.storageservice.dto.RepairDto
import com.khrustalev.storageservice.service.abstracts.RepairService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/storage/repairProcess")
class RepairController(private val repairService: RepairService) {

    @GetMapping("/find-by-car-number-actual-true")
    fun getByCarNumberAndActualTrue(@RequestParam("carNumber") carNumber: String): ResponseEntity<RepairDto> {
        return ResponseEntity(repairService.findRepairByCarNumberAndActualTrue(carNumber), HttpStatus.OK)
    }
    @GetMapping("/find-by-id")
    fun getRepairProcessById(@RequestParam repairProcessId: Long) : ResponseEntity<RepairDto> {
        return ResponseEntity(repairService.findRepairById(repairProcessId), HttpStatus.OK)
    }
    @PostMapping("/save")
    fun create(@RequestBody repairDto: RepairDto) : ResponseEntity<Long> {
        return ResponseEntity(repairService.save(repairDto), HttpStatus.OK)
    }
    @GetMapping("/repair-parts")
    fun getRepairPartsByRepairId(@RequestParam repairId: Long) : ResponseEntity<List<String>> {
        return ResponseEntity(repairService.getAllRepairParts(repairId), HttpStatus.OK)
    }
    @GetMapping("/repair-state/by-id")
    fun getRepairStatesForRepairByRepairId(@RequestParam repairId: Long) : ResponseEntity<MutableList<CarRepairStateDto>> {
        return ResponseEntity(repairService.findAllRepairStates(repairId), HttpStatus.OK)
    }
}