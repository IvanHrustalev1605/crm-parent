package com.khrustalev.administrationservice.dto

class RepairReportDto {
    var repairRequestIds: MutableList<Long> = mutableListOf()
    var carRepairStatesIds: MutableList<Long> = mutableListOf()
    var mechanicIds: MutableList<Long> = mutableListOf()
}