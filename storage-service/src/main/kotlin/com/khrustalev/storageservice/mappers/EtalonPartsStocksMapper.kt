package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.EtalonPartsStocksDto
import com.khrustalev.storageservice.entity.schems.storage.EtalonPartsStocks
import com.khrustalev.storageservice.repository.EtalonPartsDictionaryRepository
import org.springframework.stereotype.Component

@Component
class EtalonPartsStocksMapper(private val etalonPartsDictionaryRepository: EtalonPartsDictionaryRepository) {
    fun toDto(entity: EtalonPartsStocks) : EtalonPartsStocksDto = EtalonPartsStocksDto().also {
        it.id = entity.id
        it.stocks = entity.stocks
        it.etalonPartsDictionaryId = entity.etalonPartsDictionary!!.id
    }
    fun toEntity(dto: EtalonPartsStocksDto) : EtalonPartsStocks = EtalonPartsStocks().also {
        it.id = dto.id
        it.stocks = dto .stocks
        it.etalonPartsDictionary = if (dto.etalonPartsDictionaryId != null) etalonPartsDictionaryRepository.findById(dto.etalonPartsDictionaryId!!).get() else null
    }
}