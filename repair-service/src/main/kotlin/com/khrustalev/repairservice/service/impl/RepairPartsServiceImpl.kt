package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.RepairPartGroupService
import com.khrustalev.repairservice.service.RepairPartsService
import com.khrustalev.repairservice.service.TelegramService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RepairPartsServiceImpl(private val storageFeignClient: StorageFeignClient,
                             private val telegramService: TelegramService,
                             private val repairPartGroupService: RepairPartGroupService) : RepairPartsService {
    override fun install(repairPartsList: MutableList<Long>, carId: Long, ): MutableList<Long> {
        repairPartsList.forEach {
            val responseEntity = storageFeignClient.findById(it)
            if (responseEntity.statusCode.is2xxSuccessful) {
                val repairPartDto = responseEntity.body!!
                if (!repairPartDto.installed) {
                    repairPartDto.installedAt = LocalDateTime.now()
                    repairPartDto.carId = carId
                    repairPartDto.installed = true
                    repairPartGroupService.writeOffRepairPart(repairPartDto)
                    storageFeignClient.save(repairPartDto).body
                }
            }
        }
        return repairPartsList
    }

    override fun countPartStocks(): MutableMap<String, Long> {
        val countPartsStocks = storageFeignClient.countPartsStocks().body!!
        telegramService.sendMessage("Остатки по запчастям $countPartsStocks")
        return countPartsStocks
    }
}