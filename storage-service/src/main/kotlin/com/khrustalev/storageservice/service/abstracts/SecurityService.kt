package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.entity.Security

interface SecurityService {
    fun findById(securityId: Long?): Security?

}
