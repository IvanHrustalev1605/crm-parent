package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.EtalonPartsStocksDto
import com.khrustalev.storageservice.mappers.EtalonPartsStocksMapper
import com.khrustalev.storageservice.repository.EtalonPartsStocksRepository
import com.khrustalev.storageservice.service.abstracts.EtalonRepairPartsService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class EtalonRepairPartsServiceImpl(private val etalonPartsStocksRepository: EtalonPartsStocksRepository,
                                   @Lazy private val etalonPartsStocksMapper: EtalonPartsStocksMapper) : EtalonRepairPartsService {
    override fun getStocksByEtalonId(etalonId: Long): EtalonPartsStocksDto? {
        val etalonPartsStocks = etalonPartsStocksRepository.findById(etalonId)
        return if (etalonPartsStocks.isPresent) etalonPartsStocksMapper.toDto(etalonPartsStocks.get()) else null
    }

    override fun updateEtalonStocks(etalonPartsStocksDto: EtalonPartsStocksDto): Boolean {
        etalonPartsStocksRepository.save(etalonPartsStocksMapper.toEntity(etalonPartsStocksDto))
        return true
    }
}