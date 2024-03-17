package org.khrustalev.repairpertsservice.feign

import org.khrustalev.repairpertsservice.dto.RepairPartsDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@FeignClient(name = "storage-feign-client", url = "http://localhost:8888/api/storage/repairParts")
interface StorageFeignClient {
    @GetMapping("/by-id")
    fun findById(@RequestParam id: Long) : RepairPartsDto 
    @GetMapping("/all-by-ids")
    fun findByNumber(@RequestParam ids: MutableList<Long>) : MutableList<RepairPartsDto>
    @GetMapping("/all-by-parts-numbers")
    fun getByNumberList(@RequestParam partsNumberList: MutableList<UUID>) : MutableList<RepairPartsDto>
    @GetMapping("all-by-car-number")
    fun getByCarNumber(@RequestParam carNumber: String) : MutableList<RepairPartsDto>
    @GetMapping("/all-by-car-id")
    fun getByCarId(@RequestParam carId: Long) : MutableList<RepairPartsDto>
    @GetMapping("/count-by-group-name")
    fun countByGroupName(@RequestParam groupName: String) : Long 
    @GetMapping("/count-by-group-id")
    fun countByGroupId(@RequestParam groupId: Long) : Long 
    @GetMapping("/all-by-group-name")
    fun getByGroupName(@RequestParam groupName: String) : MutableList<RepairPartsDto>
    @GetMapping("/all-not-installed")
    fun getAllNotInstalled() : MutableList<RepairPartsDto>
    @GetMapping("/all-installed-for-car-by-number")
    fun getInstalledInCarByCarNumber(@RequestParam carNumber: String) : MutableList<RepairPartsDto>
    @GetMapping("/count-by-etalon-art")
    fun countByEtalonArt(@RequestParam etalonArt: String) : Long 
    @GetMapping("/all-by-dict-art")
    fun getByEtalonArt(@RequestParam dictArt: String) : MutableList<RepairPartsDto>
}