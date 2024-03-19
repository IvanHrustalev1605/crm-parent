package org.khrustalev.repairpertsservice.dto

import java.io.Serializable

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.EtalonPartsStocks}
 */
data class EtalonPartsStocksDto(
    val id: Long = 0,
    val etalonPartsDictionaryId: Long? = null,
    var stocks: Int? = null)
    : Serializable