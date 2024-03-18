package org.khrustalev.repairpertsservice.service

import org.khrustalev.repairpertsservice.dto.AcceptablePartsDto

interface StorageService {
    fun putPartsToStoragePlace(acceptablePartsDtoList: MutableList<AcceptablePartsDto>) :  Boolean
    fun takePartsFromStoragePlace(takeAwayMap: MutableMap<Long, Long>) : Boolean
}