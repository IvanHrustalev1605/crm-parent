package com.khrustalev.administrationservice.feign

import com.khrustalev.administrationservice.dto.*
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "repair-process", url = "http://host.docker.internal:8080/api/rest/repair-service")
interface RepairProcessFeignClient {
    @PostMapping("/check-arrival-car")
    fun securityCheckCar(@RequestBody arrivalQuestionnaire: ArrivalQuestionnaire, @RequestParam securityId: Long) : Boolean

    @PostMapping("/create-repair-request")
    fun createRepairRequest(@RequestBody repairRequestQuestionerDto: RepairRequestQuestionerDto) : RepairRequestDto

    @PostMapping("/create-repair-process")
    fun createRepairRequest(@RequestBody repairInfoDto: RepairInfoDto,
                            @RequestParam repairRequestList: MutableList<Long>) : RepairDto

    @GetMapping("/take-to-repair-process")
    fun takeToRepairRequest(@RequestParam repairProcessId: Long) : Boolean

    @PostMapping("/update-repair-process")
    fun updateRepairRequest(
        @RequestBody repairInfoDto: RepairInfoDto,
        @RequestParam(required = false) repairRequestList: MutableList<Long>?,
        @RequestParam repairProcessId: Long) : Boolean

    @PostMapping("/end-repair-process")
    fun endRepairRequest(@RequestBody repairInfoDto: RepairInfoDto, @RequestParam repairProcessId: Long) : Boolean

    @GetMapping("/agreed-repair-request")
    fun agreedRepairRequest(@RequestParam("repairRequestId") id: Long) : Boolean

    @GetMapping("/get-repair-report")
    fun getRepairReport(@RequestParam repairId: Long) : Boolean

    @GetMapping("/free-boxes")
    fun getFreeBoxes() : MutableList<RepairBoxDto>

    @GetMapping("/car-get-away")
    fun carGetAway(@RequestParam carNumber: String) : Boolean
    @GetMapping("/car-last-arrival-state")
    fun getActualCarArrivalState(carId: Long): CarArrivalStateDto
}