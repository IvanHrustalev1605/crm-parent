package org.khrustalev.repairpertsservice.controllers

import org.khrustalev.repairpertsservice.dto.AcceptablePartsDto
import org.khrustalev.repairpertsservice.service.StorageService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/rest/api/repair-parts-service")
class RepairPartsController(private val storageService: StorageService) {
    @PostMapping("/put-parts-to-storage-place")
    fun putPartsToStoragePlace(@RequestBody acceptablePartsDtoList: MutableList<AcceptablePartsDto>) : ResponseEntity<Boolean> {
        return ResponseEntity(storageService.putPartsToStoragePlace(acceptablePartsDtoList), HttpStatus.ACCEPTED)
    }
    @PostMapping("/take-parts-away")
    fun takePArtsFromStoragePlace(@RequestBody map: MutableMap<Long, Long>) : ResponseEntity<Boolean> {
        return ResponseEntity(storageService.takePartsFromStoragePlace(map), HttpStatus.ACCEPTED)
    }
}