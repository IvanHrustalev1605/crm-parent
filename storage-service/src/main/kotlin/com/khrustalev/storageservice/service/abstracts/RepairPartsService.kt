package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.RepairPartsDto
import com.khrustalev.storageservice.entity.schems.storage.RepairParts
import java.util.*

interface RepairPartsService {
    fun getById(id: Long) : RepairParts?
    fun save(repairPartsDto: RepairPartsDto) : RepairPartsDto
    fun getByIds(ids: MutableList<Long>) : MutableList<RepairPartsDto>
    fun getByNumberList(numberList: MutableList<UUID>) : MutableList<RepairPartsDto>
    fun getByCarNumber(carNumber: String) : MutableList<RepairPartsDto>
    fun getByCarId(carId: Long) : MutableList<RepairPartsDto>
    fun countByGroupName(groupName: String) : Long
    fun countByGroupId(groupId: Long) : Long
    fun getByGroupName(groupName: String) : MutableList<RepairPartsDto>
    fun getAllNotInstalled() : MutableList<RepairPartsDto>
    fun getInstalledInCarByCarNumber(carNumber: String) : MutableList<RepairPartsDto>
    fun countByEtalonArt(art: String) : Long
    fun convertToDto(repairParts: RepairParts) : RepairPartsDto
    fun getByEtalonArt(etalonArt: String) : MutableList<RepairPartsDto>
    fun getInstalledInRepair(repairId: Long): MutableList<RepairPartsDto>
}