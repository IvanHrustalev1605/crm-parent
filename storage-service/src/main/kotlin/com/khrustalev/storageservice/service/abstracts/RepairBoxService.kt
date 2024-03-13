package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.RepairBoxDto
import com.khrustalev.storageservice.entity.schems.storage.RepairBox

interface RepairBoxService {
    fun getById(id: Long) : RepairBoxDto
    fun getByNumber(boxNumber: Int) : RepairBoxDto
    fun getAllByIds(ids: MutableList<Long>) : MutableList<RepairBox>
    fun getAllIsFree() : List<RepairBoxDto>
    fun mapEntityToDto(entityList: MutableList<RepairBox>) : MutableList<RepairBoxDto>
    fun save(repairBoxDto: RepairBoxDto): RepairBoxDto
}
