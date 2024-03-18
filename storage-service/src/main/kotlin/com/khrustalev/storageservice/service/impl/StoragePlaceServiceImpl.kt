package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.schems.dictionary.StoragePlace
import com.khrustalev.storageservice.repository.RepairPartStoragePlaceRepository
import com.khrustalev.storageservice.repository.StoragePlaceRepository
import com.khrustalev.storageservice.service.abstracts.StoragePlaceService
import org.springframework.stereotype.Service

@Service
class StoragePlaceServiceImpl(private val storagePlaceRepository: StoragePlaceRepository) : StoragePlaceService {
    override fun findAll(): MutableList<StoragePlace> {
        return storagePlaceRepository.findAll()
    }

    override fun getById(storagePlaceId: Long): StoragePlace? {
        return storagePlaceRepository.findById(storagePlaceId).orElse(null)
    }
}