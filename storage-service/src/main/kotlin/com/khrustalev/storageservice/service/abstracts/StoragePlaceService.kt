package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.entity.schems.dictionary.StoragePlace

interface StoragePlaceService {
    fun findAll() : MutableList<StoragePlace>
    fun getById(storagePlaceId: Long): StoragePlace?
}