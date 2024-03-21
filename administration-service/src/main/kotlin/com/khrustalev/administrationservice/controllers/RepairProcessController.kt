package com.khrustalev.administrationservice.controllers

import com.khrustalev.administrationservice.dto.*
import com.khrustalev.administrationservice.service.RepairService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rest/repair-process")
@Schema(description = "Контроллер для работы с машинами: Заезд на базу, ремонт, с отслеживанием всех стадий, отъезд с базы")
class RepairProcessController(private val repairService: RepairService) {

    @Operation(summary = "1. Пропуск проезжающей машины с уточнением о необходимости ремонта", method = "POST", description = "Охранник встречает машину на въезде. Создается CarArrivalState, " +
            "основная суть которой сбор первичной информации о машине и в случае если необходимо стать на ремонт " +
            "начинает идти отсчет водителю 1ч, в течение которого нужно успеть написать заявку на ремонт(RepairRequest)",
        parameters = [Parameter(name = "securityId", description = "ID охранника который пропускает машину", required = true)],
        responses = [
            ApiResponse(responseCode = "200", description = "Машина успешно принята"),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере"),
            ApiResponse(responseCode = "403", description = "Отказано в доступе")
        ],
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(description = "dto ArrivalQuestionnaire", required = true))

    @PostMapping("/check-arrival-car")
    fun securityCheckCar(@RequestBody arrivalQuestionnaire: ArrivalQuestionnaire, @RequestParam securityId: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(repairService.securityCheckCar(arrivalQuestionnaire, securityId), HttpStatus.OK)
    }

    @Operation(summary = "2. Написание заявки на ремонт", method = "GET", description = "Написание заявки на ремонт с \"инженером\". Завяка еще должнабыть подтверждена самим инженером. " +
            "Да-да звучит очень глупо =)",
        parameters = [Parameter(name = "repairDescription", description = "Описание проблем", required = false),
                     Parameter(name = "engineerId", description = "ID \"инженера\" с которым пишется заявка"),
                     Parameter(name = "carNumber", description = "Номер машины"),
                     Parameter(name = "repairProcessId", description = "Номер процесса ремонта, " +
                             "в случае если это дополнительная заявка, а не первичная", required = false),
                     Parameter(name = "requestNumber", description = "Уникальный номер заявки. Пока что пишется руками, потом будет генерироваться автоматически", required = true)],
        responses = [
            ApiResponse(responseCode = "201", description = "Заявка успешно написана и ждет согласования", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = Schema(implementation = RepairRequestDto::class))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])
        ])
    @GetMapping("/create-repair-request")
    fun createRepairRequest(@RequestParam repairDescription: String,
                            @RequestParam engineerId: Long,
                            @RequestParam carNumber: String,
                            @RequestParam(required = false) repairProcessId: Long?,
                            @RequestParam requestNumber: Long) : ResponseEntity<RepairRequestDto> {
        return ResponseEntity(repairService.createRepairRequest(repairDescription, engineerId, carNumber, repairProcessId, requestNumber), HttpStatus.CREATED)
    }

    @Operation(summary = "4. Создание ремонтного процесса", method = "POST", description = "Создания ремонтного процесса. Создается только после подтверждения заявки на ремонт " +
            "Так же создается начальная CarRepairState, она же является родительской для всех остальных CarRepairState и у нее carRepairStatePArentID = null",
        parameters = [Parameter(name = "repairRequestList", description = "Список с ID заявок на ремонт", required = true)],
        responses = [
            ApiResponse(responseCode = "201", description = "Начальная стадия процесса ремонта успешно создана", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = Schema(implementation = RepairDto::class))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])
        ])
    @PostMapping("/create-repair-process")
    fun createRepairRequest(@RequestBody repairInfoDto: RepairInfoDto,
                            @RequestParam repairRequestList: MutableList<Long>) : ResponseEntity<RepairDto> {
        return ResponseEntity(repairService.createRepairRequest(repairInfoDto, repairRequestList), HttpStatus.CREATED)
    }

    @Operation(summary = "5. Взятие механиками машины в ремонт", method = "POST", description = "При создании начального RepairProcess механикам, указанным в repairInfoDto приходят уведомления. " +
            "В которых они должны подтвердить что принимают данную машину в ремонт. После этого RepairProcess получает статус actual. " +
            "А водитель получает уведомление, что он может проезжать в ремонтную зону и номер бокса",
        parameters = [Parameter(name = "repairProcessId", description = "ID процесса ремонта", required = true)],
        responses = [
            ApiResponse(responseCode = "200", description = "Машина успешно принята в работу. Водитель может проезжать", content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = Boolean::class))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])
        ])
    @GetMapping("/take-to-repair-process")
    fun takeToRepairRequest(@RequestParam repairProcessId: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(repairService.takeToRepairRequest(repairProcessId), HttpStatus.OK)
    }

    @Operation(summary = "6. Смена стадий ремонта", method = "POST", description = "Добавить ремонтную стадию. Можно изменять все параметры указанные в RepairInfoDto ",
        parameters = [Parameter(name = "repairRequestList", description = "Список с ID заявок на ремонт. Если нет новых заявок по этому ремонту" +
                "поля не заполняется", required = false, allowEmptyValue = true),
                     Parameter(name = "repairProcessId", description = "ID процесса ремонта к которому добавляются стадии", required = true)],
        responses = [
            ApiResponse(responseCode = "201", description = "Успешно создана новая стадия ремонта", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = Schema(implementation = RepairDto::class))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])
        ])
    @PostMapping("/update-repair-process")
    fun updateRepairRequest(
        @RequestBody repairInfoDto: RepairInfoDto,
        @RequestParam(required = false) repairRequestList: MutableList<Long>?,
        @RequestParam repairProcessId: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(repairService.updateRepairRequest(repairInfoDto, repairRequestList, repairProcessId), HttpStatus.OK)
    }

    @Operation(summary = "7. Завершение ремонта", method = "POST", description = "Завершение процесса ремонта. На этом этапе ставятся стадии DONE, освобождаются боксы, " +
            "могут быть добавлены дополнительно установленные запчасти, " +
            "actual = false",
        parameters = [Parameter(name = "repairProcessId", description = "ID ремонтного процесса", required = true)],
        responses = [
            ApiResponse(responseCode = "200", description = "Ремонт успешно завершен", content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = Boolean::class))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])
        ])
    @PostMapping("/end-repair-process")
    fun endRepairProcess(@RequestBody repairInfoDto: RepairInfoDto, @RequestParam repairProcessId: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(repairService.endRepairProcess(repairInfoDto, repairProcessId), HttpStatus.OK)
    }

    @Operation(summary = "3. Подтвердить заявку на ремонт", method = "GET", description = "Подтверждение составленной заявки на ремонт",
        parameters = [Parameter(name = "id", description = "ID заявки на ремонт (RepairRequest)", required = true)],
        responses = [
            ApiResponse(responseCode = "202", description = "Заявка подтверждена", content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = Boolean::class))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])
        ])
    @GetMapping("/agreed-repair-request")
    fun agreedRepairRequest(@RequestParam id: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(repairService.agreedRepairRequest(id), HttpStatus.ACCEPTED)
    }

    @GetMapping("/get-repair-report")
    fun getRepairReport(@RequestParam repairId: Long) : ResponseEntity<Boolean> {
        return ResponseEntity(repairService.getRepairReport(repairId), HttpStatus.CREATED)
    }
    @Operation(summary = "Получить список свободных боксов", method = "GET", description = "Получить список свободных боксов",
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно!", content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = Schema(implementation = Boolean::class))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])
        ])
    @GetMapping("/free-boxes")
    fun getFreeBoxes() : ResponseEntity<MutableList<RepairBoxDto>> {
        return ResponseEntity(repairService.getFreeBoxes(), HttpStatus.OK)
    }
    @Operation(summary = "Машина покидает базу", method = "GET", description = "Машина уезжает с базы",
        parameters = [Parameter(name = "id", description = "Номер машины", required = true)],
        responses = [
            ApiResponse(responseCode = "200", description = "Успешно!", content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = Schema(implementation = Boolean::class))]),
            ApiResponse(responseCode = "500", description = "Произошла ошибка на сервере", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "403", description = "Отказано в доступе", content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])
        ])
    @GetMapping("/car-get-away")
    fun carGetAway(@RequestParam carNumber: String) : ResponseEntity<Boolean> {
        return ResponseEntity(repairService.carGetAway(carNumber), HttpStatus.OK)
    }
}