package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.RepairRequestDto
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.RepairRequestService
import com.khrustalev.repairservice.service.TelegramService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RepairRequestServiceImpl(private val storageFeignClient: StorageFeignClient,
                               private val telegramService: TelegramService) : RepairRequestService {
    private val LOGGER: Logger = LoggerFactory.getLogger(RepairRequestServiceImpl::class.java)

    override fun createRepairRequest(
        repairDescription: String,
        engineerId: Long,
        carNumber: String,
        repairProcessId: Long?,
        requestNumber: Long
    ): RepairRequestDto {
        val repairRequestDto = RepairRequestDto().also {
            it.carId = storageFeignClient.findCarByCarNumber(carNumber)
            it.repairId = repairProcessId
            it.requestDescription = repairDescription
            it.engineerId = engineerId
            it.createDate = LocalDateTime.now()
            it.requestNumber = requestNumber
        }
        LOGGER.info("Сохраняем заявку на ремонт $repairRequestDto")
        storageFeignClient.saveRepairRequest(repairRequestDto)
        telegramService.sendMessage("Поступила новая заявка #$requestNumber" + "Нужно согласовать!")
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
        return true
        }
        return false
    }
}