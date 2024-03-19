package com.khrustalev.administrationservice.controllers

import com.khrustalev.administrationservice.dto.AcceptablePartsDto
import com.khrustalev.administrationservice.dto.RepairPartsDto
import com.khrustalev.administrationservice.service.RepairPartsServiceService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/rest/repair-parts")
@Schema(description = "Контроллер для работы с модулем хранилища запчастей repair-parts-service")
class RepairPartsServiceController(private val repairPartsServiceService: RepairPartsServiceService) {
    @Operation(description = "Получить список всех запчастей, как на складе так и нет")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Успешно"),
        ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    ])
    @GetMapping("/all")
    fun getAllRepairParts() : ResponseEntity<MutableList<RepairPartsDto>> {
        return ResponseEntity(null, HttpStatus.OK)
    }
    @Operation(description = "Положить ПРИНЯТЫЕ от поставщиков запчасти на склад т.е. запчасти лежащие в db -> storage -> repair_parts",
               parameters = [Parameter(name = "acceptablePartsDtoList",
                                    description = "Это лист с dto с фронта, в котором запчасть и склад куда ее надо положить",
                                    required = true)])
    @ApiResponses(value = [
        ApiResponse(responseCode = "202", description = "Успешно удалось положить запчасти на склад"),
        ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    ])
    @PostMapping("/put-parts-to-storage-place")
    fun putPartsToStoragePlace(@RequestBody acceptablePartsDtoList: MutableList<AcceptablePartsDto>) : ResponseEntity<Boolean>{
        return ResponseEntity(repairPartsServiceService.putPartsToStoragePlace(acceptablePartsDtoList), HttpStatus.ACCEPTED)
    }
    @Operation(description = "Взять запчасть со склада",
        parameters = [Parameter(name = "map",
            description = "Это лист с dto с фронта, в котором запчасть и склад куда ее надо положить. Передаем <IDзапчасти, IDмеханика>" +
                    "В данном случае механик общее понятие, вообще будет тот человек, который з/ч забирает",
            required = true)])
    @ApiResponses(value = [
        ApiResponse(responseCode = "202", description = "Успешно удалось взять з/ч со клада"),
        ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    ])
    @PostMapping("/take-parts-away")
    fun takePartsFromStoragePlace(@RequestBody map: MutableMap<Long, Long>) : ResponseEntity<Boolean> {
        return ResponseEntity(repairPartsServiceService.takePartsFromStoragePlace(map), HttpStatus.ACCEPTED)
    }
}