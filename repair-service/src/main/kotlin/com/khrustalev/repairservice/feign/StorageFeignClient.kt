package com.khrustalev.repairservice.feign

import com.khrustalev.repairservice.dto.*
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDateTime
import java.util.*

@FeignClient(name = "storage-feign-client", url = "http://host.docker.internal:8888/api/storage")
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
    @GetMapping("/car/by-id")
    fun getCarById(@RequestParam carId: Long) : CarDto

    /*CarStates*/
    @PostMapping("/carState/arrival/save")
    fun saveCarArrivalState(@RequestBody(required = true) carArrivalStateDto: CarArrivalStateDto) : Boolean
    @GetMapping("/carState/arrival/get-by-carNumber")
    fun getLastArrivalStateByCarNumber(@RequestParam("carNumber") carNumber: String): CarArrivalStateDto?
    @PostMapping("/carState/repair/save")
    fun saveCarRepairState(@RequestBody(required = true) car: CarRepairStateDto) : Long
    @GetMapping("/carState/arrival/get-not-written-repair-requests")
    fun getCarArrivalStatesWithNoRepairRequests() : MutableList<CarArrivalStateDto>
    @GetMapping("/carState/repair/get-previous-repair-state/by-car-id")
    fun getPreviousRepairStateByCarId(@RequestParam carId: Long) : CarRepairStateDto
    @GetMapping("/carState/arrival/get-actual-by-car-id")
    fun getLastArrivalStateByCarId(@RequestParam carId: Long) : CarArrivalStateDto

    /*Engineer*/
    @GetMapping("/engineer/find-id-by-name")
    fun getEngineerId(@RequestParam("name") engineerName: String): Long

    /*RepairProcess*/
    @GetMapping("/repairProcess/find-by-car-number-actual-true")
    fun getRepairProcessByCarNumberAndActualTrue(@RequestParam("carNumber") carNumber: String) : RepairProcessDto?
    @PostMapping("/repairProcess/save")
    fun saveRepairProcess(@RequestBody(required = true) repairProcess: RepairProcessDto) : Long

    @GetMapping("/repairProcess/find-by-id")
    fun getRepairProcessById(@RequestParam repairProcessId: Long) : RepairProcessDto
    @GetMapping("/repairProcess/repair-parts")
    fun getRepairPartsByRepairId(@RequestParam repairId: Long) : ResponseEntity<List<String>>

    /*RepairRequest*/
    @GetMapping("/repairRequest/find-all-by-car-number")
    fun getRepairRequestsByCarNumber(@RequestParam("carNumber") carNumber: String) : MutableList<RepairRequestDto>?
    @PostMapping("/repairRequest/save")
    fun saveRepairRequest(@RequestBody repairRequestDto: RepairRequestDto) : ResponseEntity<Boolean>
    @GetMapping("/repairRequest/actual-repair-request/by-car-number")
    fun findRepairRequestForActualRepairProcess(@RequestParam("actualDate") actualDate: LocalDateTime,
                                                @RequestParam("carNumber") carNumber: String): MutableList<Long>
    @PostMapping("/repairRequest/agreed")
    fun agreedRequest(@RequestParam("repairRequestId") id: Long) : RepairRequestDto
    @GetMapping("/find-by-number")
    fun getByRequestNumber(@RequestParam("repairRequestNumber") number: Long) : RepairRequestDto

    /*Drivers*/
    @GetMapping("/drivers/find-by-car-id")
    fun findByCarId(@RequestParam carId: Long) : DriverDto

    /*RepairParts*/
    @GetMapping("/repairParts/find-by-id")
    fun findById(@RequestParam id: Long) : ResponseEntity<RepairPartsDto>
    @GetMapping("/all-by-parts-numbers")
    fun getRepairPartsByNumberList(@RequestParam partsNumberList: MutableList<UUID>) : MutableList<RepairPartsDto>

    /*RepairPartsGroup*/
    @GetMapping("/repairPartsGroup/find-by-id")
    fun getRepairPartsGroupById(@RequestParam id: Long) : RepairPartGroupDto
    @PostMapping("/repairPartsGroup/save")
    fun saveRepairPartGroup(repairPartsGroupDto: RepairPartGroupDto)

    /*RepairBox*/
    @GetMapping("/repairBox/all-by-ids")
    fun getAllByIds(@RequestParam ids: MutableList<Long>) : ResponseEntity<MutableList<RepairBoxDto>>
    @GetMapping("/repairBox/free")
    fun getFreeBox() : ResponseEntity<MutableList<RepairBoxDto>>
    @GetMapping("/repairBox/by-box-number")
    fun getBoxByNumber(@RequestParam boxNumber: Int) : ResponseEntity<RepairBoxDto>
    @GetMapping("/repairBox/by-box-id")
    fun getBoxById(@RequestParam id: Long) : ResponseEntity<RepairBoxDto>
    @PostMapping("/repairBox/save")
    fun saveBox(@RequestBody repairBoxDto: RepairBoxDto) : ResponseEntity<RepairBoxDto>


}