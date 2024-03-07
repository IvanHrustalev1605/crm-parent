package com.khrustalev.repairservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class RepairPartGroupDto {
    @JsonProperty(value = "id")
    var id: Long? = null
    @JsonProperty(value = "groupName")
    var groupName: String? = null
    @JsonProperty(value = "stockBalance")
    var stockBalance: Int? = null
    @JsonProperty(value = "repairPartsListIds")
    var repairPartsListIds: MutableList<Long> = mutableListOf()
}
