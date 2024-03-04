package com.khrustalev.repairservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class SecurityDto() {
    @JsonProperty(value = "id")
    open var id: Long? = null
}
