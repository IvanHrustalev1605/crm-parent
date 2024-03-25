package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.EngineerDto
import com.khrustalev.storageservice.entity.schems.storage.Engineer
import com.khrustalev.storageservice.service.abstracts.EngineerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/storage/engineer")
class EngineerController(private val engineerService: EngineerService) {

    @GetMapping("/find-id-by-name")
    fun getIdByName(@RequestParam("name") name: String) : ResponseEntity<Long> {
        return ResponseEntity(engineerService.findIdByName(name), HttpStatus.OK)
    }
    @GetMapping("/find-by-id")
    fun getEngineerById(@RequestParam("id") id: Long): ResponseEntity<EngineerDto> {
        return ResponseEntity(engineerService.mapToDto(engineerService.findById(id)), HttpStatus.OK)
    }
}