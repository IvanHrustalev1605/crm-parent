package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.RepairPartStoragePlaceDto
import com.khrustalev.storageservice.service.abstracts.RepairPartStoragePlaceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/storage/storagePlace")
class StoragePlaceController(private val repairPartStoragePlaceService: RepairPartStoragePlaceService) {
    @PostMapping("/put-repair-parts")
    fun putPartsToStoragePlace(@RequestBody repairPartStoragePlaceDto: RepairPartStoragePlaceDto) : ResponseEntity<Boolean> {
        return ResponseEntity(repairPartStoragePlaceService.putParts(repairPartStoragePlaceDto), HttpStatus.OK)
    }
    @GetMapping("/by-repair-part-id")
    fun getRepairPartStoragePlaceByRepairPartId(@RequestParam repairPartId: Long) : ResponseEntity<RepairPartStoragePlaceDto> {
        return ResponseEntity(repairPartStoragePlaceService.getById(repairPartId), HttpStatus.OK)
    }
    @PostMapping("/save")
    fun save(@RequestBody repairPartStoragePlaceDto: RepairPartStoragePlaceDto) : ResponseEntity<Boolean> {
        return ResponseEntity(repairPartStoragePlaceService.save(repairPartStoragePlaceDto), HttpStatus.CREATED)
    }
}