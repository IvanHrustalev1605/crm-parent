package com.khrustalev.repairservice.controllers

import com.khrustalev.repairservice.dto.*
import com.khrustalev.repairservice.service.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/rest/repair-service")
class MainController(private val securityService: SecurityService,
                     private val repairProcessService: RepairProcessService,
                     private val repairRequestService: RepairRequestService,
                     private val documentService: DocumentService,
                     private val repairBoxService: RepairBoxService,
                     private val carArrivalStateService: CarArrivalStateService
) {
    @PostMapping("/check-arrival-car")
    fun securityCheckCar(@RequestBody arrivalQuestionnaire: ArrivalQuestionnaire,
                         @RequestParam("securityId") securityId: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(securityService.checkArrivalCar(arrivalQuestionnaire, securityId), HttpStatus.OK)
    }
    @GetMapping("/create-repair-request")
    fun createRepairRequest(@RequestParam repairDescription: String,
                            @RequestParam engineerId: Long,
                            @RequestParam carNumber: String,
                            @RequestParam(required = false) repairProcessId: Long?,
                            @RequestParam requestNumber: Long) : ResponseEntity<RepairRequestDto> {
        return ResponseEntity(repairRequestService.createRepairRequest(repairDescription, engineerId, carNumber, repairProcessId, requestNumber), HttpStatus.OK)
    }
    @PostMapping("/create-repair-process")
    fun createRepairRequest(@RequestBody repairInfoDto: RepairInfoDto,
                            @RequestParam repairRequestList: MutableList<Long>) : ResponseEntity<RepairProcessDto> {
        return ResponseEntity(repairProcessService.createNewRepairProcess(repairInfoDto, repairRequestList), HttpStatus.OK)
    }
    @GetMapping("/take-to-repair-process")
    fun takeToRepairRequest(@RequestParam repairProcessId: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(repairProcessService.takeRepairToWork(repairProcessId), HttpStatus.OK)
    }
    @PostMapping("/update-repair-process")
    fun updateRepairRequest(@RequestBody repairInfoDto: RepairInfoDto,
                            @RequestParam(required = false) repairRequestList: MutableList<Long>?,
                            @RequestParam repairProcessId: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(repairProcessService.updateRepairProcess(repairProcessId, repairInfoDto, repairRequestList), HttpStatus.OK)
    }
    @PostMapping("/end-repair-process")
    fun endRepairRequest(@RequestBody repairInfoDto: RepairInfoDto, @RequestParam repairProcessId: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(repairProcessService.closeRepairProcess(repairProcessId, repairInfoDto), HttpStatus.OK)
    }
    @GetMapping("/agreed-repair-request")
    fun agreedRepairRequest(@RequestParam("repairRequestId") id: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(repairRequestService.agreedRepairRequest(id), HttpStatus.OK)
    }
    @GetMapping("/get-repair-report")
    fun getRepairReport(@RequestParam repairId: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(documentService.generateRepairReport(repairId), HttpStatus.OK)
    }

    @GetMapping("/free-boxes")
    fun getFreeBoxes() : ResponseEntity<MutableList<RepairBoxDto>> {
        return ResponseEntity(repairBoxService.getFreeBoxes(), HttpStatus.OK)
    }
    @GetMapping("/car-get-away")
    fun carGetAway(@RequestParam carNumber: String) : ResponseEntity<Boolean> {
        return ResponseEntity(carArrivalStateService.carGetAway(carNumber), HttpStatus.OK)
    }
}