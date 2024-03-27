package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.CarArrivalStateDto
import com.khrustalev.storageservice.dto.CarLongRepairStateDto
import com.khrustalev.storageservice.dto.CarRepairStateDto
import com.khrustalev.storageservice.service.abstracts.CarLongRepairStatesService
import com.khrustalev.storageservice.service.abstracts.CarStateService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/storage/carState")
class CarStateController(private val carStateService: CarStateService,
                         private val carLongRepairStatesService: CarLongRepairStatesService) {
    @PostMapping("/arrival/save")
    fun saveArrivalCarState(@RequestBody(required = true) carArrivalStateDto: CarArrivalStateDto) : ResponseEntity<Boolean> {
        return ResponseEntity(carStateService.saveArrivalState(carArrivalStateDto), HttpStatus.CREATED)
    }
    @PostMapping("/repair/save")
    fun saveRepairCarState(@RequestBody(required = true) carRepairStateDto: CarRepairStateDto) : ResponseEntity<Long> {
        return ResponseEntity(carStateService.saveRepairState(carRepairStateDto), HttpStatus.CREATED)
    }
    @GetMapping("/arrival/get-by-carNumber")
    fun getLastCarArrivalStateByCarNumber(@RequestParam("carNumber") carNumber: String) : ResponseEntity<CarArrivalStateDto> {
        return ResponseEntity(carStateService.getCarArrivalStateByCarNumber(carNumber), HttpStatus.OK)
    }
    @GetMapping("/arrival/get-not-written-repair-requests")
    fun getCarArrivalStatesWithNoRepairRequests() : ResponseEntity<MutableList<CarArrivalStateDto>> {
        return ResponseEntity(carStateService.getArrivalStatesWithoutRepairRequest(), HttpStatus.OK)
    }
    @GetMapping("/repair/get-previous-repair-state/by-car-id")
    fun getPreviousRepairStateByCarId(@RequestParam carId: Long) : ResponseEntity<CarRepairStateDto> {
        return ResponseEntity(carStateService.getPreviousRepairStateByCarId(carId), HttpStatus.OK)
    }
    @GetMapping("/arrival/get-actual-by-car-id")
    fun getLastArrivalStateByCarId(@RequestParam carId: Long) : ResponseEntity<CarArrivalStateDto> {
        return ResponseEntity(carStateService.getActualArrivalStateByCarId(carId), HttpStatus.OK)
    }
    @GetMapping("/longRepair/by-id")
    fun getCarLongRepairStateById(@RequestParam id: Long) : ResponseEntity<CarLongRepairStateDto> {
        return ResponseEntity(carLongRepairStatesService.toDto(carLongRepairStatesService.findById(id)), HttpStatus.OK)
    }
    @PostMapping("/longRepair/save")
    fun saveCarLongRepairState(@RequestBody carLongRepairStateDto: CarLongRepairStateDto) : ResponseEntity<CarLongRepairStateDto> {
        return ResponseEntity(carLongRepairStatesService.save(carLongRepairStateDto), HttpStatus.CREATED)
    }
    @GetMapping("/longRepair/get-previous-repair-state/by-car-id")
    fun getPreviousLongRepairStateByCarId(@RequestParam carId: Long) : ResponseEntity<CarLongRepairStateDto?> {
        return ResponseEntity(carLongRepairStatesService.getPreviousStateByCarId(carId), HttpStatus.OK)
    }
}