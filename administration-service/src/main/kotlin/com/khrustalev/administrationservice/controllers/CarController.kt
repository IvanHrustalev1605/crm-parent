package com.khrustalev.administrationservice.controllers

import com.khrustalev.administrationservice.dto.CarArrivalStateDto
import com.khrustalev.administrationservice.dto.CarDto
import com.khrustalev.administrationservice.dto.RepairDto
import com.khrustalev.administrationservice.service.CarService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/rest/cars")
class CarController(private val carService: CarService) {
    @Operation(summary = "Поиск машины по ее номеру",
        parameters = [Parameter(
            name = "carNumber",
            description = "Номер машины", required = true)],
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")])
    @GetMapping("/by-car-number")
    fun getCarByNumber(@RequestParam carNumber: String) : ResponseEntity<CarDto> {
        return ResponseEntity(carService.getCarByNumber(carNumber), HttpStatus.OK)
    }
    @Operation(summary = "Поиск машины по ее ID",
        parameters = [Parameter(
            name = "id",
            description = "ID машины", required = true)],
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")])
    @GetMapping("/by-car-id")
    fun getCarById(@RequestParam id: Long) : ResponseEntity<CarDto> {
        return ResponseEntity(carService.getCarById(id), HttpStatus.OK)
    }
    @Operation(summary = "Поиск машины по ее Vin-номеру",
        parameters = [Parameter(
            name = "vinNumber",
            description = "Vin номер машины", required = true)],
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")])
    @GetMapping("/by-car-vin")
    fun getCarByVin(@RequestParam vinNumber: String) : ResponseEntity<CarDto> {
        return ResponseEntity(carService.getCarByVinNumber(vinNumber), HttpStatus.OK)
    }
    @Operation(summary = "Вывод списка всех машин",
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")])
    @GetMapping("/all")
    fun getAllCars() : ResponseEntity<MutableList<CarDto>> {
        return ResponseEntity(carService.getAllCars(), HttpStatus.OK)
    }
    @Operation(summary = "Вывод списка всех машин в ремонте",
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")])
    @GetMapping("/in-repair")
    fun getCarsInRepair() : ResponseEntity<MutableList<CarDto>> {
        return ResponseEntity(carService.getCarsInRepair(), HttpStatus.OK)
    }
    @Operation(summary = "Вывод списка всех машин  на базе",
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")])
    @GetMapping("/in-base")
    fun getCarsInBase() : ResponseEntity<MutableList<CarDto>> {
        return ResponseEntity(carService.getCarsInBase(), HttpStatus.OK)
    }
    @Operation(summary = "Сохранить новую машину или обновить существующую",
        parameters = [Parameter(name = "carDto", description = "dto машины", required = true)],
        responses = [
            ApiResponse(responseCode = "201", description = "Успешно"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")])
    @PostMapping("/save")
    fun saveCar(@RequestBody carDto: CarDto) : ResponseEntity<CarDto> {
        return ResponseEntity(carService.saveCar(carDto), HttpStatus.CREATED)
    }
    @Operation(summary = "Получить все приезды на базу машины", method = "GET",
        parameters = [Parameter(name = "carId", description = "ID машины", required = true)],
        responses = [
            ApiResponse(responseCode = "201", description = "Успешно"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
        ])
    @GetMapping("/all-arrives")
    fun getAllArrivesByCarId(@RequestParam carId: Long) : ResponseEntity<MutableList<CarArrivalStateDto>> {
        return ResponseEntity(carService.getAllArrives(carId), HttpStatus.OK)
    }
    @Operation(summary = "Получить все ремонты машины", method = "GET",
        parameters = [Parameter(name = "carId", description = "ID машины", required = true),
                      Parameter(name = "actual", description = "true - текущие, null - все которые были", required = false)],
        responses = [
            ApiResponse(responseCode = "201", description = "Успешно"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")])
    @GetMapping("/all-repairs")
    fun getAllRepairsByCarId(@RequestParam carId: Long, @RequestParam(required = false) actual: Boolean) : ResponseEntity<MutableList<RepairDto>> {
        return ResponseEntity(carService.getRepairsByCarNumber(carId, actual), HttpStatus.OK)
    }
}