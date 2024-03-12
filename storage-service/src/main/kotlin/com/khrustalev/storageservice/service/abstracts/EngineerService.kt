package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.entity.schems.storage.Engineer

interface EngineerService {
    fun findById(id: Long): Engineer
    fun findIdByName(name: String) : Long?
}