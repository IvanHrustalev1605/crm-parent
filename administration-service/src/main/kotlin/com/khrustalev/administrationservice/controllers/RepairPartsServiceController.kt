package com.khrustalev.administrationservice.controllers

import com.khrustalev.administrationservice.dto.AcceptablePartsDto
import com.khrustalev.administrationservice.dto.RepairPartsDto
import com.khrustalev.administrationservice.service.RepairPartsServiceService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Arrays

@RestController
@RequestMapping("/api/rest/repair-parts")
@Schema(description = "Контроллер для работы с модулем хранилища запчастей repair-parts-service")
class RepairPartsServiceController(private val repairPartsServiceService: RepairPartsServiceService) {

    @Operation(summary = "Все запчасти", description = "Получить список всех запчастей, как на складе так и нет")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Успешно", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = Schema(implementation = RepairPartsDto::class))]),
        ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера" ,content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = String::class))]),
        ApiResponse(responseCode = "403", description = "Нет доступа", content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = String::class))])
    ])
    @GetMapping("/all")
    fun getAllRepairParts() : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(null, HttpStatus.OK)
    }

    @Operation(summary = "Положить запчасти на склады", description = "Положить ПРИНЯТЫЕ от поставщиков запчасти на склад т.е. запчасти лежащие в db -> storage -> repair_parts")
    @ApiResponses(value = [
        ApiResponse(responseCode = "202", description = "Успешно", content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = Boolean::class))]),
        ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера" ,content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = String::class))]),
        ApiResponse(responseCode = "403", description = "Нет доступа", content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = String::class))])
    ])
    @PostMapping("/put-parts-to-storage-place")
    fun putPartsToStoragePlace(@RequestBody acceptablePartsDtoList: MutableList<AcceptablePartsDto>) : ResponseEntity<Boolean>{
        return ResponseEntity(repairPartsServiceService.putPartsToStoragePlace(acceptablePartsDtoList), HttpStatus.ACCEPTED)
    }

    @Operation(summary = "Взять запчасти со склада", description = "Взять запчасть со склада")
    @ApiResponses(value = [
        ApiResponse(responseCode = "202", description = "Успешно", content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = Boolean::class))]),
        ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера" ,content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = String::class))]),
        ApiResponse(responseCode = "403", description = "Нет доступа", content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = String::class))])
    ])
    @PostMapping("/take-parts-away")
    fun takePartsFromStoragePlace(@RequestBody map: MutableMap<Long, Long>) : ResponseEntity<Boolean> {
        return ResponseEntity(repairPartsServiceService.takePartsFromStoragePlace(map), HttpStatus.ACCEPTED)
    }
    @Operation(summary = "Запчасти установленные во время процесса ремонта", description = "Получить список всех запчастей, которые были установлены во время ремонта",
        parameters = [Parameter(name = "repairId", description = "ID ремонтного процесса", required = true)])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Успешно", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = ArraySchema(items = Schema(implementation = RepairPartsDto::class)))]),
        ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера" ,content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = String::class))]),
        ApiResponse(responseCode = "403", description = "Нет доступа", content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = String::class))])
    ])
    @GetMapping("/installed-parts-in-repair")
    fun getInstalledPartsDuringRepairProcess(@RequestParam repairId: Long) : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(repairPartsServiceService.getInstalledPartsDuringRepair(repairId), HttpStatus.OK)
    }
}