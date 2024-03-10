package org.khrustalev.documentservice

import org.khrustalev.documentservice.service.WordService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(private val wordService: WordService) {
    @GetMapping
    fun test() : ResponseEntity<Boolean> {
        return ResponseEntity(wordService.generateWordDocument(), HttpStatus.OK)
    }
}