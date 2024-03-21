package com.khrustalev.administrationservice.controllers

import com.khrustalev.administrationservice.dto.CarArrivalStateDto
import com.khrustalev.administrationservice.dto.CarDto
import com.khrustalev.administrationservice.dto.DriverDto
import com.khrustalev.administrationservice.dto.RepairDto
import com.khrustalev.administrationservice.service.DriverService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rest/drivers")
@Schema(description = "Контроллер по работе с водителями")
class DriversController(private val driverService: DriverService) {

    @Operation(summary = "Получить всех водителей", method = "GET",
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно!", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = ArraySchema(items = Schema(implementation = DriverDto::class), schema = Schema(implementation = DriverDto::class)))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])])
    @GetMapping("/all")
    fun getAllDrivers() : ResponseEntity<MutableList<DriverDto>> {
        return ResponseEntity(driverService.getAllDrivers() ,HttpStatus.OK)
    }
    @Operation(summary = "Получить водителя по ID", method = "GET",
        parameters = [Parameter(name = "id", description = "ID водителя в БД", required = true)],
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно!", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = Schema(implementation = DriverDto::class))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])])
    @GetMapping("/by-id")
    fun getDriversById(@RequestParam id: Long) : ResponseEntity<DriverDto> {
        return ResponseEntity(driverService.getDriversById(id) ,HttpStatus.OK)
    }
    @Operation(summary = "Получить водителя или по имени или по фамилии или по отчеству", method = "GET", description = "Т.к. могут быть совпадения в поиске, возвращаем лист",
        parameters = [Parameter(name = "fio", description = "Имя отчество или фамилия водителя", required = true)],
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно!", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = ArraySchema(items = Schema(implementation = DriverDto::class)))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])])
    @GetMapping("/by-fio")
    fun getDriversByFIO(@RequestParam fio: String) : ResponseEntity<MutableList<DriverDto>> {
        return ResponseEntity(driverService.getDriversByFIO(fio) ,HttpStatus.OK)
    }
    @Operation(summary = "Получить водителя по номеру лицензии", method = "GET",
        parameters = [Parameter(name = "license", description = "Лицензия водителя(условное понятие, может быть например номером прав)", required = true)],
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно!", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = Schema(implementation = DriverDto::class))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])])
    @GetMapping("/by-license")
    fun getDriversByLicense(@RequestParam license: String) : ResponseEntity<DriverDto> {
        return ResponseEntity(driverService.getDriversByLicense(license) ,HttpStatus.OK)
    }
    @Operation(summary = "Изменить или сохранить водителя", method = "POST",
        responses = [
            ApiResponse(responseCode = "201", description = "Успешно!", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = Schema(implementation = DriverDto::class))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])])
    @PostMapping("/save")
    fun saveDrivers(@RequestBody driverDto: DriverDto) : ResponseEntity<DriverDto> {
        return ResponseEntity(driverService.saveDrivers(driverDto), HttpStatus.CREATED)
    }
    @Operation(summary = "Получить машину на которой ездит водитель по его ID", method = "GET",
        parameters = [Parameter(name = "driverId", description = "ID водителя", required = true)],
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно!", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = Schema(implementation = CarDto::class))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])])
    @GetMapping("/car")
    fun getCarByDriverId(@RequestParam driverId: Long) : ResponseEntity<CarDto> {
        return ResponseEntity(driverService.getCarByDriverId(driverId) ,HttpStatus.OK)
    }
    @Operation(summary = "Получить все ремонты которые были у водителя по его ID", method = "GET",
        parameters = [Parameter(name = "driverId", description = "ID водителя", required = true)],
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно!", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = ArraySchema(items = Schema(implementation = RepairDto::class), schema = Schema(implementation = RepairDto::class)))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])])
    @GetMapping("/all-repairs")
    fun getAllRepairs(@RequestParam driverId: Long) : ResponseEntity<MutableList<RepairDto>> {
        return ResponseEntity(driverService.getAllRepairs(driverId) ,HttpStatus.OK)
    }
    @Operation(summary = "Получить все приезды на базу которые были у водителя по его ID", method = "GET",
        parameters = [Parameter(name = "driverId", description = "ID водителя", required = true)],
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно!", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = ArraySchema(items = Schema(implementation = CarArrivalStateDto::class), schema = Schema(implementation = CarArrivalStateDto::class)))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])])
    @GetMapping("/all-arrivals")
    fun getAllArrivals(@RequestParam driverId: Long) : ResponseEntity<MutableList<CarArrivalStateDto>> {
        return ResponseEntity(driverService.getAllArrivals(driverId) ,HttpStatus.OK)
    }
}