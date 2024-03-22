package org.khrustalev.repairpartsservice.service.impl

import org.khrustalev.repairpartsservice.dto.AcceptablePartsDto
import org.khrustalev.repairpartsservice.dto.RepairPartStoragePlaceDto
import org.khrustalev.repairpartsservice.feign.RepairPartsFeignClient
import org.khrustalev.repairpartsservice.feign.StoragePlaceFeignClient
import org.khrustalev.repairpartsservice.service.StorageService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class StorageServiceImpl(private val repairPartsFeignClient: RepairPartsFeignClient,
                         private val storagePlaceFeignClient: StoragePlaceFeignClient) : StorageService {

    /**
     * @param acceptablePartsDtoList - dtolist состоящий из запчастей и склада куда ее положить
     * */
    override fun putPartsToStoragePlace(acceptablePartsDtoList: MutableList<AcceptablePartsDto>): Boolean {
        acceptablePartsDtoList.forEach {x ->
            RepairPartStoragePlaceDto().also {
                it.storagePlaceId = x.storagePlaceId
                it.inPlace = true
                it.createdAt = LocalDateTime.now()
                it.updatedAt = LocalDateTime.now()
                it.repairPartsId = x.repairPartsDto!!.id
                storagePlaceFeignClient.putPartsToStoragePlace(it)
            }
        }
        return true
    }
    /**
     * @param takeAwayMap - RepairPartId -> MechanicId
     * @return - возвращаем true если все прошло успешно и з/ч забрали со склада
     * */
    override fun takePartsFromStoragePlace(takeAwayMap: MutableMap<Long, Long>): Boolean {
        takeAwayMap.entries.forEach {
            val repairPartStoragePlaceDto =
                storagePlaceFeignClient.getRepairPartStoragePlaceByRepairPartId(it.key)
            repairPartStoragePlaceDto.inPlace = false
            repairPartStoragePlaceDto.mechanicId = it.value
            storagePlaceFeignClient.updateStoragePlace(repairPartStoragePlaceDto)
        }
        return true
    }
}




























