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
    /*
    * -Логика метода-
    * Машина заезжает на базу
    * Охранник встречает, отмечает время прибытия
    * Ставит отметку нужен ли ремонт
    * Сущность *CarState* сохраняется в БД
    * */
    override fun checkArrivalCar(arrivalQuestionnaire: ArrivalQuestionnaire, securityId: Long): Boolean {
        val carState = CarArrivalStateDto()
        val carId = storageFeignClient.findCarByCarNumber(arrivalQuestionnaire.carNumber!!)
        val engineerId: Long = storageFeignClient.getEngineerId(arrivalQuestionnaire.engineerName!!)

        carState.needRepair = arrivalQuestionnaire.needRepair!!
        carState.arrivalTime = LocalDateTime.now()
        carState.mileage = arrivalQuestionnaire.mileage
        carState.carId = carId
        if (arrivalQuestionnaire.needRepair!!) {
            carState.engineerId = engineerId
            telegramService.sendMessage("Братишкааа! Там тачка ${arrivalQuestionnaire.carNumber} в ремонт приехала, заебал, работай давай \uD83D\uDE18")
        }
        carState.descriptionProblems = arrivalQuestionnaire.carDescriptionProblems!!
        carState.checkUp = arrivalQuestionnaire.carCheckUp
        carState.receivingSecurity = securityId
        LOGGER.info("Сохраняем carState $carState...")
        if (storageFeignClient.saveCarArrivalState(carState)) {
            LOGGER.info("CarState успешно сохранен!")
            return true
        }
        LOGGER.info("При сохранение CarState произошла ошибка")
        return false
    }
}