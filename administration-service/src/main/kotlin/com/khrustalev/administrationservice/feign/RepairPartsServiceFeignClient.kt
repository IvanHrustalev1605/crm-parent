package com.khrustalev.administrationservice.feign

import com.khrustalev.administrationservice.dto.AcceptablePartsDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "repair-parts-storage", url = "http://localhost:8182/api/rest/api/repair-parts-service")
interface RepairPartsServiceFeignClient {
    @PostMapping("/put-parts-to-storage-place")
    fun putPartsToStoragePlace(@RequestBody acceptablePartsDtoList: MutableList<AcceptablePartsDto>) : ResponseEntity<Boolean>

}