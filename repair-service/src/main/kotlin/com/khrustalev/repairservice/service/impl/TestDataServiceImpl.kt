package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.feign.StorageFeignClient
import com.khrustalev.repairservice.service.TestDataService
import org.springframework.stereotype.Service

@Service
class TestDataServiceImpl(private val storageFeignClient: StorageFeignClient) : TestDataService {
    override fun generateTestData(): Boolean {
        return storageFeignClient.createTestDataInDb().body!!
    }
}