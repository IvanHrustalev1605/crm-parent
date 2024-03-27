package com.khrustalev.administrationservice.feign

import com.khrustalev.administrationservice.dto.AcceptablePartsDto
import com.khrustalev.administrationservice.dto.RepairPartsDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "repair-parts-storage", url = "http://localhost:8182/api/rest/api/repair-parts-service")
interface RepairPartsServiceFeignClient {
    @PostMapping("/put-parts-to-storage-place")
    fun putPartsToStoragePlace(@RequestBody acceptablePartsDtoList: MutableList<AcceptablePartsDto>) : ResponseEntity<Boolean>
    @PostMapping("/take-parts-away")
    fun takePartsFromStoragePlace(@RequestBody map: MutableMap<Long, Long>) : Boolean
    @GetMapping("/installed-parts/by-repair-id")
    fun getInstalledRepairParts(@RequestParam repairId: Long) : MutableList<RepairPartsDto>
    @GetMapping("/all")
    fun getAllParts(@RequestParam size: Int): MutableList<RepairPartsDto>
}