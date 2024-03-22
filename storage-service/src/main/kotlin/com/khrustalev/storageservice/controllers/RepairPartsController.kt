package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.EtalonPartsStocksDto
import com.khrustalev.storageservice.dto.RepairPartsDto
import com.khrustalev.storageservice.entity.schems.storage.EtalonPartsStocks
import com.khrustalev.storageservice.service.abstracts.EtalonRepairPartsService
import com.khrustalev.storageservice.service.abstracts.RepairPartsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/storage/repairParts")
class RepairPartsController(private val repairPartsService: RepairPartsService,
                            private val etalonRepairPartsService: EtalonRepairPartsService) {

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
    @GetMapping("/get-etalon-parts-by-id")
    fun getEtalonPartsStocks(@RequestParam id: Long) : ResponseEntity<EtalonPartsStocksDto> {
        return ResponseEntity(etalonRepairPartsService.getStocksByEtalonId(id), HttpStatus.OK)
    }
    @PostMapping("/update-etalon-parts-stocks")
    fun updatePartsStocks(@RequestBody etalonPartsStocksDto: EtalonPartsStocksDto) : ResponseEntity<Boolean> {
        return ResponseEntity(etalonRepairPartsService.updateEtalonStocks(etalonPartsStocksDto), HttpStatus.CREATED)
    }
    @PostMapping("/save")
    fun saveRepairPart(@RequestBody repairPartsDto: RepairPartsDto) : ResponseEntity<RepairPartsDto> {
        return ResponseEntity(repairPartsService.save(repairPartsDto), HttpStatus.CREATED)
    }
    @GetMapping("/installed")
    fun getInstalledPartsByRepairId(@RequestParam repairId: Long) : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(repairPartsService.getInstalledInRepair(repairId), HttpStatus.OK)
    }
}