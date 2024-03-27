package com.khrustalev.administrationservice.controllers

import com.khrustalev.administrationservice.dto.LongRepairProcessDto
import com.khrustalev.administrationservice.dto.RepairInfoDto
import com.khrustalev.administrationservice.service.LongRepairService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/rest/long-repair")
@Schema(description = "Работа с длительным процессом ремонта. Если он требуется в процессе обычного. " +
        "Завершение происходит через RepairProcessController.endRepairProcess")
class LongRepairController(private val longRepairService: LongRepairService) {
    @Operation(summary = "Создать длительный процесс ремонта", description = "Создается длительный процесс ремонта к уже начатому обычному ремонту, " +
            "когда в этом есть необходимость. Создаются CarLongRepairStates и тд", method = "POST",
        parameters = [Parameter(name = "repairId", description = "ID уже НАЧАТОГО обычного ремонта")],
        responses = [
            ApiResponse(responseCode = "202", description = "Успешно", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = Schema(implementation = LongRepairProcessDto::class))]),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера" ,content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Нет доступа", content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = String::class))])
        ])
    @PostMapping("/create")
    fun createLongRepair(@RequestBody repairInfoDto: RepairInfoDto, @RequestParam repairId: Long) : ResponseEntity<LongRepairProcessDto> {
        return ResponseEntity(longRepairService.create(repairInfoDto, repairId), HttpStatus.CREATED)
    }
    @Operation(summary = "Обновить стадии длительного процесса ремонта", description = "Дополняются стадии для последнего длительного процесса ремонта по ID машины", method = "POST",
        responses = [
            ApiResponse(responseCode = "202", description = "Успешно", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = Schema(implementation = LongRepairProcessDto::class))]),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера" ,content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Нет доступа", content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = String::class))])
        ])
    @PostMapping("/update")
    fun updateLongRepair(@RequestBody repairInfoDto: RepairInfoDto) : ResponseEntity<LongRepairProcessDto> {
        return ResponseEntity(longRepairService.update(repairInfoDto), HttpStatus.CREATED)
    }
}