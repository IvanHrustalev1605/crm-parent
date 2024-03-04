package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.entity.CarRepairState

interface CarRepairStateService {
    fun findById(id: Long) : CarRepairState
    fun getDtoById(ids: MutableList<Long>) : MutableList<CarRepairState>?
}
