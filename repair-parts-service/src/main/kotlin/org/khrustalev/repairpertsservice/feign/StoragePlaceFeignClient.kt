package org.khrustalev.repairpertsservice.feign

import org.khrustalev.repairpertsservice.dto.RepairPartStoragePlaceDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "storage-service-storage-place-feign-client", url = "http://host.docker.internal:8888/api/storage/storagePlace")
interface StoragePlaceFeignClient {
    @PostMapping("/put-repair-parts")
    fun putPartsToStoragePlace(@RequestBody repairPartStoragePlaceDto: RepairPartStoragePlaceDto) : Boolean
    @GetMapping("/by-repair-part-id")
    fun getRepairPartStoragePlaceByRepairPartId(@RequestParam repairPartId: Long) : RepairPartStoragePlaceDto

    @PostMapping("/save")
    fun updateStoragePlace(@RequestBody repairPartStoragePlaceDto: RepairPartStoragePlaceDto) : Boolean
}