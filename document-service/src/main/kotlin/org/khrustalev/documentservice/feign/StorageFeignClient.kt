package org.khrustalev.documentservice.feign

import org.khrustalev.documentservice.dto.RepairReportDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient (name = "storage-feign-client", url = "http://localhost:8888/api/storage")
interface StorageFeignClient {
    @GetMapping("/reports/repair-report")
    fun generateRepairReport(@RequestParam id: Long) : ResponseEntity<RepairReportDto>
}