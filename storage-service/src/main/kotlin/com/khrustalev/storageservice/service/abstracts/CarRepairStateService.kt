package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.entity.schems.storage.CarRepairState

interface CarRepairStateService {
    fun findById(id: Long) : CarRepairState
    fun getDtoById(ids: MutableList<Long>) : MutableList<CarRepairState>?
}
