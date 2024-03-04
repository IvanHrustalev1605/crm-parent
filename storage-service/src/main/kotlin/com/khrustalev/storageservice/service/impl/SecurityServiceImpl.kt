package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.Security
import com.khrustalev.storageservice.exception.NotFoundEntityException
import com.khrustalev.storageservice.repository.SecurityRepository
import com.khrustalev.storageservice.service.abstracts.SecurityService
import org.springframework.stereotype.Service

@Service
class SecurityServiceImpl(private val securityRepository: SecurityRepository) : SecurityService {
    override fun findById(securityId: Long?): Security? {
        return securityRepository.findById(securityId!!).orElseThrow { NotFoundEntityException("Security with ID $securityId not found!") }
    }
}