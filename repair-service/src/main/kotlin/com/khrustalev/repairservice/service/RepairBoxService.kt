package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.RepairBoxDto

interface RepairBoxService {
    fun getFreeBoxes() : MutableList<RepairBoxDto>
    fun busyBox(boxNumber: Int) : Boolean
    fun setBoxFree(boxNumber: Int) : Boolean
    fun getBoxByNumber(boxNumber: Int) : RepairBoxDto
}
