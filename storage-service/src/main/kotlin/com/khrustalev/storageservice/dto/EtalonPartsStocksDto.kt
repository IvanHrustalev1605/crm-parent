package com.khrustalev.storageservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/**
 * DTO for {@link com.khrustalev.storageservice.entity.schems.storage.EtalonPartsStocks}
 */
data class EtalonPartsStocksDto(
    @JsonProperty(value = "id")
    var id: Long = 0,
    @JsonProperty(value = "etalonPartsDictionaryId")
    var etalonPartsDictionaryId: Long? = null,
    @JsonProperty(value = "stocks")
    var stocks: Int? = null)
    : Serializable