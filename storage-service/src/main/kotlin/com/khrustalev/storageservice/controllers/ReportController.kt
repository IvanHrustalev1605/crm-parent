package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.RepairReportDto
import com.khrustalev.storageservice.service.abstracts.DocumentService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/storage/reports")
class ReportController(private var documentService: DocumentService) {
    @GetMapping("/repair-report")
    fun generateRepairReport(@RequestParam id: Long) : ResponseEntity<RepairReportDto> {
        return ResponseEntity(documentService.generateRepairReport(id), HttpStatus.OK)
    }
}