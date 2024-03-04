package com.khrustalev.repairservice.controllers

import com.khrustalev.repairservice.dto.ArrivalQuestionnaire
import com.khrustalev.repairservice.dto.CarRepairStateDto
import com.khrustalev.repairservice.service.CarRepairStateService
import com.khrustalev.repairservice.service.RepairProcessService
import com.khrustalev.repairservice.service.SecurityService
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/main")
@Schema(description = "Контроллер который будет имитировать работу сервиса, пока нет разграничения по ролям и авторизаций")
class MainController(private val securityService: SecurityService,
                     private val repairProcessService: RepairProcessService,
                     private val carRepairStateService: CarRepairStateService) {

    @Operation(summary = "Охранник на въезде отмечает прибывшею машину. " +
            "В случае необходимости ремонта, назначается инженер, который будет ответственен за процесс ремонта")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Успешно!"),
        ApiResponse(responseCode = "500", description = "Ошибка!")
    ])
    @PostMapping("/check-arrival-car")
    fun securityCheckCar(
                         @Parameter(description = "Небольшой чек лист, в котором необходимо указать номер машины, необходимость ремонта, " +
                                "краткое описание проблем и назначить инженера, если нужен ремонт")
                         @RequestBody arrivalQuestionnaire: ArrivalQuestionnaire,
                         @Parameter(description = "Пока нет авторизаций, охранник пропустивший машину присваивается вручную")
                         @RequestParam("securityId") securityId: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(securityService.checkArrivalCar(arrivalQuestionnaire, securityId), HttpStatus.OK)
    }
    @GetMapping("/car-arrival-sate")
    fun carState(@RequestParam carNumber: String): ResponseEntity<CarRepairStateDto> {
        return ResponseEntity(carRepairStateService.createNewRepairState(carNumber), HttpStatus.OK)
    }
}