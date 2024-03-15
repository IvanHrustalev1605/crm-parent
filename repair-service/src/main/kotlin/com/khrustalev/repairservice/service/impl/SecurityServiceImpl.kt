package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.ArrivalQuestionnaire
import com.khrustalev.repairservice.dto.CarArrivalStateDto
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.SecurityService
import com.khrustalev.repairservice.service.TelegramService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SecurityServiceImpl(private val storageFeignClient: StorageFeignClient,
                          private val telegramService: TelegramService) : SecurityService {
    private val LOGGER: Logger = LoggerFactory.getLogger(SecurityServiceImpl::class.java)

    override fun checkArrivalCar(arrivalQuestionnaire: ArrivalQuestionnaire, securityId: Long): Boolean {
        val carState = CarArrivalStateDto()
        val carId = storageFeignClient.findCarByCarNumber(arrivalQuestionnaire.carNumber!!)
        val engineerId: Long = storageFeignClient.getEngineerId(arrivalQuestionnaire.engineerName!!)
        carState.inBase = true
        carState.needRepair = arrivalQuestionnaire.needRepair!!
        carState.arrivalTime = LocalDateTime.now()
        carState.carId = carId
        carState.stateChangeTime = LocalDateTime.now()
        carState.receivingSecurity = securityId
        carState.is30Notificate = false
        carState.is15Notificate = false


        if (arrivalQuestionnaire.needRepair!!) {
            carState.timeToMakeRequestStart = LocalDateTime.now()
            carState.timeToMakeRequestEnd = LocalDateTime.now().plusHours(1)
            carState.engineerId = engineerId
            carState.descriptionProblems = arrivalQuestionnaire.carDescriptionProblems!!
        }
        LOGGER.info("Сохраняем carState $carState...")
        if (storageFeignClient.saveCarArrivalState(carState)) {
            LOGGER.info("CarState успешно сохранен!")
            if (carState.needRepair!!) {
                telegramService.sendMessage("Братишкааа! Там тачка ${arrivalQuestionnaire.carNumber} в ремонт приехала, заебал, работай давай \uD83D\uDE18")
            }
            return true
        }
        LOGGER.info("При сохранение CarState произошла ошибка")
        return false
    }
}