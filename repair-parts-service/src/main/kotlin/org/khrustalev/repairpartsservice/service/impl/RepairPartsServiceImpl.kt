package org.khrustalev.repairpartsservice.service.impl

import org.khrustalev.repairpartsservice.dto.EtalonPartsStocksDto
import org.khrustalev.repairpartsservice.dto.RepairPartsDto
import org.khrustalev.repairpartsservice.feign.RepairPartsFeignClient
import org.khrustalev.repairpartsservice.service.RepairPartsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import java.time.LocalDateTime
import java.util.UUID
import kotlin.streams.toList

@Service
class RepairPartsServiceImpl(private val repairPartsFeignClient: RepairPartsFeignClient) : RepairPartsService {
    private val logger: Logger = LoggerFactory.getLogger(RepairPartsServiceImpl::class.java)

    override fun installPartByNumber(partNumberList: List<UUID>, carId: Long): MutableList<Long> {
        val partsList = repairPartsFeignClient.getByNumberList(partNumberList.toMutableList())
        if (!CollectionUtils.isEmpty(partsList)) {
            partsList.forEach {
                it.installed = true
                it.installedAt = LocalDateTime.now()
                it.carId = carId
                if (CollectionUtils.isEmpty(writeOffRepairParts(mutableListOf(it.id!!)))) {
                    repairPartsFeignClient.save(it)
                } else {
                    logger.info("Нельзя установить запчасть с номером ${it.number} потому что количество остатков ее эталонной группе" +
                            "недопустимо для списания!")
                }
            }
            return partsList.stream()
                .mapToLong { it.id!! }
                .toList().toMutableList()
        }
        logger.info("No parts found by NUMBER: $partNumberList")
        return mutableListOf()
    }

    override fun updatePart(repairPartsDto: RepairPartsDto): Boolean {
        return repairPartsFeignClient.save(repairPartsDto) != null
    }

    override fun acceptRepairParts(repairPartsList: MutableList<RepairPartsDto>): Boolean {
        TODO("Not yet implemented")
    }
/**
 * @description: списывание запчастей при установке
 * @param repairPartsIdList - лист с ID запчастей
 * @return - возвращаем List c ID запчастей, количество которых не удалось изменить
 *              если List пустой, то все прошло удачно
 * */
// TODO: можно изменить логику сохрангения, что бы не каждый раз по 1 записи обновлять БД
    override fun writeOffRepairParts(repairPartsIdList: MutableList<Long>): MutableList<Long> {
        val partsWithExceptions: MutableList<Long> = mutableListOf()
        repairPartsIdList.forEach {
            val repairPartsDto = repairPartsFeignClient.findById(it)
            val etalonPartsStocks: EtalonPartsStocksDto =
                repairPartsFeignClient.getEtalonPartsStocks(repairPartsDto.etalonPartsDictionaryId!!)
            if (etalonPartsStocks.stocks!! == 0) {
               partsWithExceptions.add(it)
                logger.info("Для эталонной группы запчастей c ID ${etalonPartsStocks.etalonPartsDictionaryId} невозможно списать остатки!")
            }
            etalonPartsStocks.stocks = etalonPartsStocks.stocks!!.minus(1)
            repairPartsFeignClient.updatePartsStocks(etalonPartsStocks)
        }
    logger.info("Количество запчастей в БД для всех позиций успешно изменено!")
    return partsWithExceptions
    }

    override fun getInstalledByRepairId(repairId: Long): MutableList<RepairPartsDto> {
        return repairPartsFeignClient.getInstalledPartsByRepairId(repairId)
    }

}



















