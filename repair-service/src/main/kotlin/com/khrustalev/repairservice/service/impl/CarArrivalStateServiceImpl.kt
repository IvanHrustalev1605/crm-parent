package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.CarArrivalStateDto
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.CarArrivalStateService
import com.khrustalev.repairservice.service.TelegramService
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class CarArrivalStateServiceImpl(private val storageFeignClient: StorageFeignClient,
                                 private val telegramService: TelegramService) : CarArrivalStateService {
    override fun sendNotification15minutes() {
        storageFeignClient.getCarArrivalStatesWithNoRepairRequests()
            .filter { !it.is15Notificate!!}
            .forEach {
                if (ChronoUnit.MINUTES.between(LocalDateTime.now(), it.repairRequestWriteUpTo) in 0 .. 15) {
                    telegramService.sendMessage("Водителю с ${storageFeignClient.findByCarId(it.carId!!).license!!} осталось менее 15 минут что бы оформить заявку на ремонт!\n" +
                            "Поторопись ленивая сучка!")
                    it.is15Notificate = true
                    storageFeignClient.saveCarArrivalState(it)
                }
            }
    }
    override fun sendNotification30minutes() {
        storageFeignClient.getCarArrivalStatesWithNoRepairRequests()
            .filter { !it.is30Notificate!!}
            .forEach {
                if (ChronoUnit.MINUTES.between(LocalDateTime.now(), it.repairRequestWriteUpTo) in 15 .. 30) {
                    telegramService.sendMessage("Водителю с ${storageFeignClient.findByCarId(it.carId!!).license!!} осталось менее 30 минут что бы оформить заявку на ремонт!\n" +
                            "Поторопись ленивая сучка!")
                    it.is30Notificate = true
                    storageFeignClient.saveCarArrivalState(it)
                }
            }
    }

    override fun setRepairRequest(carNumber: String): Boolean {
        val stateDto = storageFeignClient.getLastArrivalStateByCarNumber(carNumber)
        stateDto!!.repairRequestWrittenIn = LocalDateTime.now()
        stateDto.repairRequestWritten = true
        storageFeignClient.saveCarArrivalState(stateDto)
        return true
    }

    override fun getStateByCarNumber(carNumber: String): CarArrivalStateDto? {
        return storageFeignClient.getLastArrivalStateByCarNumber(carNumber)
    }

    override fun carGetAway(carNumber: String): Boolean {
        val stateDto = getStateByCarNumber(carNumber)
        stateDto?.inBase = false
        if (stateDto != null) {
            storageFeignClient.saveCarArrivalState(stateDto)
            return true
        }
        return false
    }
}