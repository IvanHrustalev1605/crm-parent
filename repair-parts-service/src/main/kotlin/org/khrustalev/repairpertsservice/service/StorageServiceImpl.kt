package org.khrustalev.repairpertsservice.service

import org.khrustalev.repairpertsservice.feign.StorageFeignClient
import org.springframework.stereotype.Service

@Service
class StorageServiceImpl(private val storageFeignClient: StorageFeignClient) : StorageService