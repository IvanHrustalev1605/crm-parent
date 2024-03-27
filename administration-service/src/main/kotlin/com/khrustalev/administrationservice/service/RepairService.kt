package com.khrustalev.administrationservice.service

import com.khrustalev.administrationservice.dto.*
import org.springframework.util.MultiValueMap

interface RepairService {
    fun securityCheckCar(arrivalQuestionnaire: ArrivalQuestionnaire, securityId: Long) : Boolean
    fun createRepairRequest( repairRequestQuestionerDto: RepairRequestQuestionerDto) : RepairRequestDto
    fun createRepairRequest( repairInfoDto: RepairInfoDto, repairRequestList: MutableList<Long>) : RepairDto
    fun takeToRepairRequest( repairProcessId: Long) : Boolean
    fun updateRepairRequest(repairInfoDto: RepairInfoDto, repairRequestList: MutableList<Long>?, repairProcessId: Long) : Boolean
    fun endRepairProcess(repairInfoDto: RepairInfoDto, repairProcessId: Long) : Boolean
    fun agreedRepairRequest(id: Long) : Boolean
    fun getRepairReport( repairId: Long) : Boolean
    fun getFreeBoxes() : MutableList<RepairBoxDto>
    fun carGetAway( carNumber: String) : Boolean
    fun getLastCarArrivalStateByCarId(carId: Long) : CarArrivalStateDto
    fun getInfoAboutAllRepairs(): MutableList<FullInfoRepairDto>
}