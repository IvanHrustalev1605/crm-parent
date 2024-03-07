package com.khrustalev.storageservice.dto

import com.khrustalev.storageservice.entity.RepairParts
import jakarta.persistence.Column
import jakarta.persistence.JoinTable
import jakarta.persistence.OneToMany

class RepairPartsGroupDto {
    var id: Long? = null
    var groupName: String? = null
    var stockBalance: Int? = null
    var repairPartsListIds: MutableList<Long> = mutableListOf()
}