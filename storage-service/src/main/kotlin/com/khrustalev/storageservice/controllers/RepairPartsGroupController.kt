package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.RepairPartsGroupDto
import com.khrustalev.storageservice.service.abstracts.RepairPartGroupService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/storage/repairPartsGroup")
class RepairPartsGroupController(private val repairPartGroupService: RepairPartGroupService) {
    @GetMapping("/find-by-id")
    fun getRepairPartsGroupById(@RequestParam id: Long) : ResponseEntity<RepairPartsGroupDto> {
        return ResponseEntity(repairPartGroupService.findById(id), HttpStatus.OK)
    }
    @PostMapping("/save")
    fun saveRepairPartGroup(repairPartsGroupDto: RepairPartsGroupDto) :ResponseEntity<Boolean>{
        return ResponseEntity(repairPartGroupService.save(repairPartsGroupDto), HttpStatus.CREATED)
    }
}