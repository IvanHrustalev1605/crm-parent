package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.dto.CarArrivalStateDto
import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.CarArrivalStateService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CarArrivalStateServiceImpl(private val storageFeignClient: StorageFeignClient) : CarArrivalStateService {
    override fun getArrivalStatesWithNoRepairRequest(): MutableMap<String, LocalDateTime> {
        val result: MutableMap<String, LocalDateTime> = mutableMapOf()
        storageFeignClient.getCarArrivalStatesWithNoRepairRequests().forEach {
            result[storageFeignClient.findByCarId(it.carId!!).license!!] = it.repairRequestWriteUpTo!!
        }
        return result
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
}