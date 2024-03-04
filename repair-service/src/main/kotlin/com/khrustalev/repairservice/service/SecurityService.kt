package com.khrustalev.repairservice.service

import com.khrustalev.repairservice.dto.ArrivalQuestionnaire

interface SecurityService {

    fun checkArrivalCar(arrivalQuestionnaire: ArrivalQuestionnaire, securityId: Long) : Boolean
}