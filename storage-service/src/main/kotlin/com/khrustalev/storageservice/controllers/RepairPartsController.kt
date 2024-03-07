package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.RepairPartsDto
import com.khrustalev.storageservice.service.abstracts.RepairPartsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/storage/repairParts")
class RepairPartsController(private val repairPartsService: RepairPartsService) {
    @GetMapping("/find-by-id")
    fun findById(@RequestParam id: Long) : ResponseEntity<RepairPartsDto> {
        return ResponseEntity(repairPartsService.getById(id), HttpStatus.OK)
    }
    @PostMapping("/save")
    fun save(@RequestBody repairPartsDto: RepairPartsDto) : ResponseEntity<RepairPartsDto> {
        return ResponseEntity(repairPartsService.save(repairPartsDto), HttpStatus.CREATED)
    }
    @GetMapping("/partsStocks")
    fun contPartsStocks() : ResponseEntity<MutableMap<String, Long>> {
        return ResponseEntity(repairPartsService.countStocksPArts(), HttpStatus.OK)
    }

}