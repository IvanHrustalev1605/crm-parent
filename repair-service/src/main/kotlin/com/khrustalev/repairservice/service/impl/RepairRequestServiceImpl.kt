package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.RepairRequestDto
import com.khrustalev.repairservice.dto.RepairRequestQuestionerDto
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.CarArrivalStateService
import com.khrustalev.repairservice.service.RepairRequestService
import com.khrustalev.repairservice.service.TelegramService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class RepairRequestServiceImpl(private val storageFeignClient: StorageFeignClient,
                               private val telegramService: TelegramService,
                               private val carArrivalStateService: CarArrivalStateService) : RepairRequestService {
    private val LOGGER: Logger = LoggerFactory.getLogger(RepairRequestServiceImpl::class.java)

    override fun createRepairRequest(repairRequestQuestionerDto: RepairRequestQuestionerDto
    ): RepairRequestDto {
        val repairRequestDto = RepairRequestDto().also {
            it.carId = repairRequestQuestionerDto.carId
            it.repairId = repairRequestQuestionerDto.repairProcessId!!
            it.requestDescription = repairRequestQuestionerDto.repairDescription
            it.engineerId = repairRequestQuestionerDto.engineerId
            it.createDate = LocalDateTime.now()
            it.requestNumber = Random.nextInt(560000, 1000000).toLong()
        }
        LOGGER.info("Сохраняем заявку на ремонт $repairRequestDto")
        storageFeignClient.saveRepairRequest(repairRequestDto)
        telegramService.sendMessage("Поступила новая заявка #${repairRequestDto.repairId}" + "Нужно согласовать!")
        return repairRequestDto
    }

    override fun getAllRepairRequestsByCarNumber(carNumber: String): MutableList<RepairRequestDto>? {
        return storageFeignClient.getRepairRequestsByCarNumber(carNumber)
    }

    override fun getActualRepairRequest(actualDate: LocalDateTime, carNumber: String): MutableList<Long> {
        return storageFeignClient.findRepairRequestForActualRepairProcess(actualDate, carNumber)
    }

    override fun agreedRepairRequest(id: Long): Boolean {
        val result = storageFeignClient.agreedRequest(id)
        if (result.agreed!!) {
            val driver = storageFeignClient.findByCarId(result.carId!!)
            telegramService.sendMessage("ВОДИТЕЛЮ с \'ЛИЦЕНЗИЕЙ\' ${driver.license}: Заявка № ${result.requestNumber} согласованна. \n" +
                    "Пожалуйста, заполните бланк ремонта по ссылке *место для ссылки* " +
                    "И можете проезжать в зону ремонта!")
            carArrivalStateService.setRepairRequest(storageFeignClient.getCarById(result.carId!!).number!!)
        return true
        }
        return false
    }
}