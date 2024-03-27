package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.dto.LongRepairProcessDto

interface LongRepairProcessService {
    fun save(longRepairProcessDto: LongRepairProcessDto): LongRepairProcessDto?
    fun findActualByCarId(carId: Long): LongRepairProcessDto?
    fun findByRepairId(repairProcessId: Long): LongRepairProcessDto?
    fun getAll(): MutableList<LongRepairProcessDto>
}