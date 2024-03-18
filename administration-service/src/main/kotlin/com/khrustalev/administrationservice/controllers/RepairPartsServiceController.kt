package com.khrustalev.administrationservice.controllers

import com.khrustalev.administrationservice.dto.AcceptablePartsDto
import com.khrustalev.administrationservice.dto.RepairPartsDto
import com.khrustalev.administrationservice.service.RepairPartsServiceService
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/rest/repair-parts")
@Schema(description = "Контроллер для работы с модулем хранилища запчастей repair-parts-service")
class RepairPartsServiceController(private val repairPartsServiceService: RepairPartsServiceService) {
    @GetMapping("/all")
    fun getAllRepairParts() : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(null, HttpStatus.OK)
    }
    @PostMapping("/put-parts-to-storage-place")
    fun putPartsToStoragePlace(@RequestBody acceptablePartsDtoList: MutableList<AcceptablePartsDto>) : ResponseEntity<Boolean>{
        return ResponseEntity(repairPartsServiceService.putPartsToStoragePlace(acceptablePartsDtoList), HttpStatus.ACCEPTED)
    }
}