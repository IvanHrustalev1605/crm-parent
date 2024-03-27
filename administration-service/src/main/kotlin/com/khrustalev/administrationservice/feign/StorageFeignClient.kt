package com.khrustalev.administrationservice.feign

import com.khrustalev.administrationservice.dto.*
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@FeignClient(name = "storage-feign-client", url = "http://localhostlocalhost:8888/api/storage")
interface StorageFeignClient {
    /*Cars*/
    @GetMapping("/car/find-by-vin")
    fun getByVin(@RequestParam vin: String) : ResponseEntity<CarDto>
    @GetMapping("/car/by-id")
    fun getCarById(@RequestParam carId: Long) : ResponseEntity<CarDto>
    @GetMapping("/car/all")
    fun getAllCars() : ResponseEntity<MutableList<CarDto>>
    @GetMapping("/car/by-number")
    fun getCarByNumber(@RequestParam carNumber: String) : ResponseEntity<CarDto>
    @GetMapping("/car/in-repair")
    fun getCarsInRepair() : ResponseEntity<MutableList<CarDto>>
    @GetMapping("/car/in-base")
    fun getCarsInBase() : ResponseEntity<MutableList<CarDto>>
    @PostMapping("/car/save")
    fun saveCar(@RequestBody carDto: CarDto) : ResponseEntity<CarDto>
    @DeleteMapping("/car/delete")
    fun deleteCarByNumberOrVin(@RequestParam v: String) : ResponseEntity<Boolean>
    @GetMapping("/car/repairs")
    fun getRepairsByCarId(@RequestParam carId: Long,
                          @RequestParam(required = false) actual: Boolean) : ResponseEntity<MutableList<RepairDto>>
    @GetMapping("/car/all-arrives")
    fun getAllArrivesByCarId(@RequestParam carId: Long): ResponseEntity<MutableList<CarArrivalStateDto>>

    /*-----*/
    /*Engineers*/

    /*-----*/
    /*RepairRequests*/

    /*-----*/
    /*Repair*/

    /*-----*/
    /*Security*/

    /*-----*/
    /*Mechanics*/
    @GetMapping("/mechanics/all")
    fun getAllMechanics() : MutableList<MechanicDto>
    @GetMapping("/mechanics/by-id")
    fun getMechanicById(@RequestParam id: Long) : MechanicDto
    @GetMapping("/mechanics/by-fio")
    fun getMechanicByFIO(@RequestParam fio: String) : MechanicDto
    @PostMapping("/mechanic/save")
    fun saveMechanic(@RequestBody mechanicDto: MechanicDto) : MechanicDto
    /*-----*/
    /*Drivers*/
    @GetMapping("/drivers/all")
    fun getAllDrivers() : MutableList<DriverDto>
    @GetMapping("/drivers/by-id")
    fun getDriversById(@RequestParam id: Long) : DriverDto
    @GetMapping("/drivers/by-fio")
    fun getDriversByFIO(@RequestParam fio: String) : MutableList<DriverDto>
    @PostMapping("/drivers/save")
    fun saveDrivers(@RequestBody driverDto: DriverDto) : DriverDto
    @GetMapping("/drivers/car")
    fun getCarByDriverId(@RequestParam driverId: Long) : CarDto
    @GetMapping("/drivers/all-repairs")
    fun getAllRepairs(@RequestParam driverId: Long) : MutableList<RepairDto>
    @GetMapping("/drivers/all-arrivals")
    fun getAllArrivals(@RequestParam driverId: Long) : MutableList<CarArrivalStateDto>
    @GetMapping("/drivers/by-license")
    fun getDriversByLicense(@RequestParam license: String): DriverDto
    /*-----*/

}