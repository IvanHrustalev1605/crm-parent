package com.khrustalev.administrationservice.dto

class RepairPartsGroupDto {
    var id: Long? = null
    var groupName: String? = null
    var stockBalance: Int? = null
    var repairPartsListIds: MutableList<Long> = mutableListOf()
}