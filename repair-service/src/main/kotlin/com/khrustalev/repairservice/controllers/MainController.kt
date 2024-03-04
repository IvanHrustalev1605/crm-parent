package com.khrustalev.repairservice.controllers

import com.khrustalev.repairservice.dto.*
import com.khrustalev.repairservice.service.CarRepairStateService
import com.khrustalev.repairservice.service.RepairProcessService
import com.khrustalev.repairservice.service.RepairRequestService
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
                     private val carRepairStateService: CarRepairStateService,
                     private val repairRequestService: RepairRequestService) {

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
    @Operation(summary = "Создаем заявку на ремонтные работы. Она может быть создана как новая так и привязана к какому то уже созданому ремонтному процессу")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Успешно!"),
        ApiResponse(responseCode = "500", description = "Ошибка!")
    ])
    @GetMapping("/create-repair-request")
    fun createRepairRequest(@RequestParam repairDescription: String,
                            @RequestParam engineerId: Long,
                            @RequestParam carNumber: String,
                            @RequestParam(required = false) repairProcessId: Long?,
                            @Parameter(description = "УНИКАЛЬНЫЙ номер заявки. В дальнейшем будет авто генерация. Пока что ручками")
                            @RequestParam requestNumber: Long) : ResponseEntity<RepairRequestDto> {
        return ResponseEntity(repairRequestService.createRepairRequest(repairDescription, engineerId, carNumber, repairProcessId, requestNumber), HttpStatus.OK)
    }
    @Operation(summary = "Создание процесса ремонта")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Успешно!"),
        ApiResponse(responseCode = "500", description = "Ошибка!")
    ])
    @PostMapping("/create-repair-request")
    fun createRepairRequest(@RequestParam carNumber: String,
                            @RequestBody repairInfoDto: RepairInfoDto,
                            @RequestParam repairRequestList: MutableList<Long>) : ResponseEntity<RepairProcessDto> {
        return ResponseEntity(repairProcessService.createRepairProcess(carNumber, repairInfoDto, repairRequestList), HttpStatus.OK)
    }
    @Operation(summary = "Добавление стадии ремонта")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Успешно!"),
        ApiResponse(responseCode = "500", description = "Ошибка!")
    ])
    @PostMapping("/create-repair-car-state/change")
    fun createRepairCarStateChange(@RequestBody repairInfoDto: RepairInfoDto) : ResponseEntity<CarRepairStateDto> {
        return ResponseEntity(carRepairStateService.createChangeRepairState(repairInfoDto), HttpStatus.OK)
    }
    @Operation(summary = "Создание новой стадии ремонта при первоначальном становлении на ремонт")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Успешно!"),
        ApiResponse(responseCode = "500", description = "Ошибка!")
    ])
    @PostMapping("/create-repair-car-state/new")
    fun createRepairCarStateNew(@RequestBody repairInfoDto: RepairInfoDto,
                                @RequestParam carNumber: String) : ResponseEntity<CarRepairStateDto> {
        return ResponseEntity(carRepairStateService.createNewRepairState(carNumber, repairInfoDto), HttpStatus.OK)
    }

}