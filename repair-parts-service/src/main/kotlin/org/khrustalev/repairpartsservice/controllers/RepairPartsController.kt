package org.khrustalev.repairpartsservice.controllers

import org.khrustalev.repairpartsservice.dto.AcceptablePartsDto
import org.khrustalev.repairpartsservice.dto.RepairPartsDto
import org.khrustalev.repairpartsservice.service.RepairPartsService
import org.khrustalev.repairpartsservice.service.StorageService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/rest/api/repair-parts-service")
class RepairPartsController(private val storageService: StorageService,
                            private val repairPartsService: RepairPartsService) {
    @PostMapping("/put-parts-to-storage-place")
    fun putPartsToStoragePlace(@RequestBody acceptablePartsDtoList: MutableList<AcceptablePartsDto>) : ResponseEntity<Boolean> {
        return ResponseEntity(storageService.putPartsToStoragePlace(acceptablePartsDtoList), HttpStatus.ACCEPTED)
    }
    @PostMapping("/take-parts-away")
    fun takePArtsFromStoragePlace(@RequestBody map: MutableMap<Long, Long>) : ResponseEntity<Boolean> {
        return ResponseEntity(storageService.takePartsFromStoragePlace(map), HttpStatus.ACCEPTED)
    }
    @PostMapping("/install-parts-to-car")
    fun installPartToCar(@RequestBody partNumberList: List<UUID>,
                         @RequestParam carId: Long) : ResponseEntity<MutableList<Long>> {
        return ResponseEntity(repairPartsService.installPartByNumber(partNumberList, carId), HttpStatus.ACCEPTED)
    }
    @PostMapping("/write-off-parts")
    fun writeOffParts(@RequestBody repairPartsIdList: MutableList<Long>) : ResponseEntity<MutableList<Long>> {
        return ResponseEntity(repairPartsService.writeOffRepairParts(repairPartsIdList), HttpStatus.ACCEPTED)
    }
    @GetMapping("/installed-parts/by-repair-id")
    fun getInstalledRepairParts(@RequestParam repairId: Long) : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(repairPartsService.getInstalledByRepairId(repairId), HttpStatus.OK)
    }
}