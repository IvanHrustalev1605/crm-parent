package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.RepairBoxDto
import com.khrustalev.storageservice.service.abstracts.RepairBoxService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/storage/repairBox")
class RepairBoxController(private val repairBoxService: RepairBoxService) {
    @GetMapping("/all-by-ids")
    fun getAllByIds(@RequestParam ids: MutableList<Long>) : ResponseEntity<MutableList<RepairBoxDto>> {
        return ResponseEntity(repairBoxService.mapEntityToDto(repairBoxService.getAllByIds(ids)), HttpStatus.OK)
    }
    @GetMapping("/free")
    fun getFreeBox() : ResponseEntity<MutableList<RepairBoxDto>> {
        return ResponseEntity(repairBoxService.getAllIsFree().toMutableList(), HttpStatus.OK)
    }
    @GetMapping("/by-box-number")
    fun getBoxByNumber(@RequestParam boxNumber: Int) : ResponseEntity<RepairBoxDto> {
        return ResponseEntity(repairBoxService.getByNumber(boxNumber), HttpStatus.OK)
    }
    @GetMapping("/by-box-id")
    fun getBoxById(@RequestParam id: Long) : ResponseEntity<RepairBoxDto> {
        return ResponseEntity(repairBoxService.getById(id), HttpStatus.OK)
    }
    @PostMapping("/save")
    fun save(@RequestBody repairBoxDto: RepairBoxDto) : ResponseEntity<RepairBoxDto> {
        return ResponseEntity(repairBoxService.save(repairBoxDto), HttpStatus.CREATED)
    }
}