package com.khrustalev.repairservice.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "document-service", url = "http://host.docker.internal:8899/api/document-service")
interface DocumentFeignClient {
    @GetMapping("/repair-report")
    fun getRepairReport(@RequestParam repairId: Long) : ResponseEntity<Boolean>
}