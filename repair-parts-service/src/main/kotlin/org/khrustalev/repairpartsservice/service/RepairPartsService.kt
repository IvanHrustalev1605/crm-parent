package org.khrustalev.repairpartsservice.service

import org.khrustalev.repairpartsservice.dto.RepairPartsDto
import java.util.UUID

interface RepairPartsService {
    fun installPartByNumber(partNumberList: List<UUID>, carId: Long) : MutableList<Long>
    fun updatePart(repairPartsDto: RepairPartsDto) : Boolean
    fun acceptRepairParts(repairPartsList: MutableList<RepairPartsDto>) : Boolean
    fun writeOffRepairParts(repairPartsIdList: MutableList<Long>) : MutableList<Long>
    fun getInstalledByRepairId(repairId: Long): MutableList<RepairPartsDto>
}