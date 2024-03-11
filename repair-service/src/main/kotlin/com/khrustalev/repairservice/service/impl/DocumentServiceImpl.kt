package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.feign.DocumentFeignClient
import com.khrustalev.repairservice.service.DocumentService
import org.springframework.stereotype.Service

@Service
class DocumentServiceImpl(private val documentFeignClient: DocumentFeignClient) : DocumentService {
    override fun generateRepairReport(repairId: Long): Boolean {
        return documentFeignClient.getRepairReport(repairId).body!!
    }
}