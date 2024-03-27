package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.CarRepairStateDto
import com.khrustalev.storageservice.dto.LongRepairProcessDto
import com.khrustalev.storageservice.dto.RepairDto
import com.khrustalev.storageservice.service.abstracts.LongRepairProcessService
import com.khrustalev.storageservice.service.abstracts.RepairService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/storage/repairProcess")
class RepairController(private val repairService: RepairService,
                       private val longRepairProcessService: LongRepairProcessService) {

    @GetMapping("/find-by-car-number-actual-true")
    fun getByCarNumberAndActualTrue(@RequestParam("carNumber") carNumber: String): ResponseEntity<RepairDto> {
        return ResponseEntity(repairService.findRepairByCarNumberAndActualTrue(carNumber), HttpStatus.OK)
    }
    @GetMapping("/find-by-id")
    fun getRepairProcessById(@RequestParam repairProcessId: Long) : ResponseEntity<RepairDto> {
        return ResponseEntity(repairService.findRepairById(repairProcessId), HttpStatus.OK)
    }
    @PostMapping("/save")
    fun create(@RequestBody repairDto: RepairDto) : ResponseEntity<Long> {
        return ResponseEntity(repairService.save(repairDto), HttpStatus.OK)
    }

    @GetMapping("/repair-state/by-id")
    fun getRepairStatesForRepairByRepairId(@RequestParam repairId: Long) : ResponseEntity<MutableList<CarRepairStateDto>> {
        return ResponseEntity(repairService.findAllRepairStates(repairId), HttpStatus.OK)
    }
    @PostMapping("/longRepair/save")
    fun saveLongRepairProcess(@RequestBody longRepairProcessDto: LongRepairProcessDto) : ResponseEntity<LongRepairProcessDto?> {
        return ResponseEntity(longRepairProcessService.save(longRepairProcessDto), HttpStatus.CREATED)
    }
    @PostMapping("/longRepair/actual-by-carId")
    fun getActualLongRepairProcessByCarId(@RequestParam carId: Long) : ResponseEntity<LongRepairProcessDto> {
        return ResponseEntity(longRepairProcessService.findActualByCarId(carId), HttpStatus.OK)
    }
    @GetMapping("/longRepair/get-by-repair-id")
    fun getLongRepairProcessByRepairId(@RequestParam repairProcessId: Long) : ResponseEntity<LongRepairProcessDto> {
        return ResponseEntity(longRepairProcessService.findByRepairId(repairProcessId), HttpStatus.OK)
    }
    @GetMapping("/all-fast")
    fun getAllFastRepairProcess() : ResponseEntity<MutableList<RepairDto>> {
        return ResponseEntity(repairService.getAll(), HttpStatus.OK)
    }
    @GetMapping("/all-long")
    fun getAllLongRepairProcess() : ResponseEntity<MutableList<LongRepairProcessDto>> {
        return ResponseEntity(longRepairProcessService.getAll(), HttpStatus.OK)
    }
}