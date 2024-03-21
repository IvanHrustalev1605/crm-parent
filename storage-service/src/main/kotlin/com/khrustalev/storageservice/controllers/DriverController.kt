package com.khrustalev.storageservice.controllers

import com.khrustalev.storageservice.dto.CarArrivalStateDto
import com.khrustalev.storageservice.dto.CarDto
import com.khrustalev.storageservice.dto.DriverDto
import com.khrustalev.storageservice.dto.RepairDto
import com.khrustalev.storageservice.service.abstracts.DriverService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/storage/drivers")
class DriverController(private val driverService: DriverService) {
    @GetMapping("/find-by-car-id")
    fun getByCarId(@RequestParam carId : Long) : ResponseEntity<DriverDto> {
        return ResponseEntity(driverService.findByCarId(carId), HttpStatus.OK)
    }
    @GetMapping("/all")
    fun getAllDrivers() : ResponseEntity<MutableList<DriverDto>> {
        return ResponseEntity(driverService.getAllDrivers() ,HttpStatus.OK)
    }

    @GetMapping("/by-id")
    fun getDriversById(@RequestParam id: Long) : ResponseEntity<DriverDto> {
        return ResponseEntity(driverService.getDriversById(id) ,HttpStatus.OK)
    }
    @GetMapping("/by-fio")
    fun getDriversByFIO(@RequestParam fio: String) : ResponseEntity<MutableList<DriverDto>> {
        return ResponseEntity(driverService.getDriversByFIO(fio) ,HttpStatus.OK)
    }
    @GetMapping("/by-license")
    fun getDriversByLicense(@RequestParam license: String) : ResponseEntity<DriverDto> {
        return ResponseEntity(driverService.getDriversByLicense(license) ,HttpStatus.OK)
    }
    @PostMapping("/save")
    fun saveDrivers(@RequestBody driverDto: DriverDto) : ResponseEntity<DriverDto> {
        return ResponseEntity(driverService.saveDrivers(driverDto), HttpStatus.CREATED)
    }

    @GetMapping("/car")
    fun getCarByDriverId(@RequestParam driverId: Long) : ResponseEntity<CarDto> {
        return ResponseEntity(driverService.getCarByDriverId(driverId) ,HttpStatus.OK)
    }

    @GetMapping("/all-repairs")
    fun getAllRepairs(@RequestParam driverId: Long) : ResponseEntity<MutableList<RepairDto>> {
        return ResponseEntity(driverService.getAllRepairs(driverId) ,HttpStatus.OK)
    }

    @GetMapping("/all-arrivals")
    fun getAllArrivals(@RequestParam driverId: Long) : ResponseEntity<MutableList<CarArrivalStateDto>> {
        return ResponseEntity(driverService.getAllArrivals(driverId) ,HttpStatus.OK)
    }
}