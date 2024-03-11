package org.khrustalev.documentservice

import org.khrustalev.documentservice.service.WordService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/document-service")
class TestController(private val wordService: WordService) {

    @GetMapping("/repair-report")
    fun getRepairReport(@RequestParam repairId: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(wordService.generateRepairReport(repairId), HttpStatus.OK)
    }
}