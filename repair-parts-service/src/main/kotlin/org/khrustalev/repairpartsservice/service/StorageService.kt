package org.khrustalev.repairpartsservice.service

import org.khrustalev.repairpartsservice.dto.AcceptablePartsDto

interface StorageService {
    fun putPartsToStoragePlace(acceptablePartsDtoList: MutableList<AcceptablePartsDto>) :  Boolean
    fun takePartsFromStoragePlace(takeAwayMap: MutableMap<Long, Long>) : Boolean
}