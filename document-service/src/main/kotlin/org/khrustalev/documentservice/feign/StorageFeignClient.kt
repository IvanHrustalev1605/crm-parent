package org.khrustalev.documentservice.feign

import org.khrustalev.documentservice.dto.CarRepairStateDto
import org.khrustalev.documentservice.dto.RepairReportDto
import org.khrustalev.documentservice.dto.RepairRequestDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient (name = "storage-feign-client", url = "http://localhostlocalhost:8888/api/storage")
interface StorageFeignClient {
    @GetMapping("/reports/repair-report")
    fun generateRepairReport(@RequestParam id: Long) : ResponseEntity<RepairReportDto>


    @GetMapping("/repairRequest/by-id")
    fun getByRepairRequestId(@RequestParam id: Long) : ResponseEntity<RepairRequestDto>
    @GetMapping("/repairProcess/repair-state/by-id")
    fun getRepairStatesForRepairByRepairId(@RequestParam repairId: Long) : ResponseEntity<MutableList<CarRepairStateDto>>
}