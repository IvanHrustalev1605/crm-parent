package com.khrustalev.administrationservice.service

import com.khrustalev.administrationservice.dto.AcceptablePartsDto

interface RepairPartsServiceService {
    fun putPartsToStoragePlace(acceptablePartsDtoList: MutableList<AcceptablePartsDto>) : Boolean
}