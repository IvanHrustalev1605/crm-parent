package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.RepairPartsDto
import com.khrustalev.storageservice.service.abstracts.RepairPartsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/storage/repairParts")
class RepairPartsController(private val repairPartsService: RepairPartsService) {

    @GetMapping("/by-id")
    fun findById(@RequestParam id: Long) : ResponseEntity<RepairPartsDto> {
        return ResponseEntity(repairPartsService.getById(id)?.let { repairPartsService.convertToDto(it) }, HttpStatus.OK)
    }
    @GetMapping("/all-by-ids")
    fun findByNumber(@RequestParam ids: MutableList<Long>) : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(repairPartsService.getByIds(ids), HttpStatus.OK)
    }
    @GetMapping("/all-by-parts-numbers")
    fun getByNumberList(@RequestParam partsNumberList: MutableList<UUID>) : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(repairPartsService.getByNumberList(partsNumberList), HttpStatus.OK)
    }
    @GetMapping("all-by-car-number")
    fun getByCarNumber(@RequestParam carNumber: String) : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(repairPartsService.getByCarNumber(carNumber), HttpStatus.OK)
    }
    @GetMapping("/all-by-car-id")
    fun getByCarId(@RequestParam carId: Long) : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(repairPartsService.getByCarId(carId), HttpStatus.OK)
    }
    @GetMapping("/count-by-group-name")
    fun countByGroupName(@RequestParam groupName: String) : ResponseEntity<Long> {
        return ResponseEntity(repairPartsService.countByGroupName(groupName), HttpStatus.OK)
    }
    @GetMapping("/count-by-group-id")
    fun countByGroupId(@RequestParam groupId: Long) : ResponseEntity<Long> {
        return ResponseEntity(repairPartsService.countByGroupId(groupId), HttpStatus.OK)
    }
    @GetMapping("/all-by-group-name")
    fun getByGroupName(@RequestParam groupName: String) : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(repairPartsService.getByGroupName(groupName), HttpStatus.OK)
    }
    @GetMapping("/all-not-installed")
    fun getAllNotInstalled() : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(repairPartsService.getAllNotInstalled(), HttpStatus.OK)
    }
    @GetMapping("/all-installed-for-car-by-number")
    fun getInstalledInCarByCarNumber(@RequestParam carNumber: String) : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(repairPartsService.getInstalledInCarByCarNumber(carNumber), HttpStatus.OK)
    }
    @GetMapping("/count-by-etalon-art")
    fun countByEtalonArt(@RequestParam etalonArt: String) : ResponseEntity<Long> {
        return ResponseEntity(repairPartsService.countByEtalonArt(etalonArt), HttpStatus.OK)
    }
    @GetMapping("/all-by-dict-art")
    fun getByEtalonArt(@RequestParam dictArt: String) : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(repairPartsService.getByEtalonArt(dictArt), HttpStatus.OK)
    }
}