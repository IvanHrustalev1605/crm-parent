package com.khrustalev.repairservice.feign

import com.khrustalev.repairservice.dto.*
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDateTime

@FeignClient(name = "storage-feign-client", url = "http://localhost:8888/api/storage")
interface StorageFeignClient {
    /*Test Data*/
    @GetMapping("/test-data")
    fun createTestDataInDb() : ResponseEntity<Boolean>

    /*Security*/
    @GetMapping
    fun getSecurityById(id: Long): SecurityDto

    /*Cars*/
    @GetMapping("/car/find-by-number")
    fun findCarByCarNumber(@RequestParam("carNumber") carNumber: String) : Long

    /*CarStates*/
    @PostMapping("/carState/arrival/save")
    fun saveCarArrivalState(@RequestBody(required = true) car: CarArrivalStateDto) : Boolean
    @GetMapping("/carState/arrival/get-by-carNumber")
    fun getLastArrivalStateByCarNumber(@RequestParam("carNumber") carNumber: String): CarArrivalStateDto?
    @PostMapping("/carState/repair/save")
    fun saveCarRepairState(@RequestBody(required = true) car: CarRepairStateDto) : Boolean
    /*Engineer*/
    @GetMapping("/engineer/find-id-by-name")
    fun getEngineerId(@RequestParam("name") engineerName: String): Long

    /*RepairProcess*/
    @GetMapping("/repairProcess/find-by-car-number-actual-true")
    fun getRepairProcessByCarNumberAndActualTrue(@RequestParam("carNumber") carNumber: String) : RepairProcessDto?
    @PostMapping("/repairProcess/save")
    fun saveRepairProcess(@RequestBody(required = true) repairProcess: RepairProcessDto) : Boolean

    /*RepairRequest*/
    @GetMapping("/repairRequest/find-all-by-car-number")
    fun getRepairRequestsByCarNumber(@RequestParam("carNumber") carNumber: String) : MutableList<RepairRequestDto>?
    @PostMapping("/repairRequest/save")
    fun saveRepairRequest(@RequestBody repairRequestDto: RepairRequestDto) : ResponseEntity<Boolean>
    @GetMapping("/repairRequest/actual-repair-request/by-car-number")
    fun findRepairRequestForActualRepairProcess(@RequestParam("actualDate") actualDate: LocalDateTime,
                                                @RequestParam("carNumber") carNumber: String): MutableList<Long>


}