package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.EtalonPartsStocksDto

interface EtalonRepairPartsService {
    fun getStocksByEtalonId(etalonId: Long) : EtalonPartsStocksDto?
    fun updateEtalonStocks(etalonPartsStocksDto: EtalonPartsStocksDto) : Boolean
}
