package com.khrustalev.administrationservice.service

import com.khrustalev.administrationservice.dto.*

interface RepairService {
    fun securityCheckCar(arrivalQuestionnaire: ArrivalQuestionnaire, securityId: Long) : Boolean
    fun createRepairRequest( repairDescription: String, engineerId: Long, carNumber: String, repairProcessId: Long?, requestNumber: Long) : RepairRequestDto
    fun createRepairRequest( repairInfoDto: RepairInfoDto, repairRequestList: MutableList<Long>) : RepairDto
    fun takeToRepairRequest( repairProcessId: Long) : Boolean
    fun updateRepairRequest(repairInfoDto: RepairInfoDto, repairRequestList: MutableList<Long>?, repairProcessId: Long) : Boolean
    fun endRepairProcess(repairInfoDto: RepairInfoDto, repairProcessId: Long) : Boolean
    fun agreedRepairRequest(id: Long) : Boolean
    fun getRepairReport( repairId: Long) : Boolean
    fun getFreeBoxes() : MutableList<RepairBoxDto>
    fun carGetAway( carNumber: String) : Boolean
}