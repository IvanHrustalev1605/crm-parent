package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.entity.schems.storage.Security

interface SecurityService {
    fun findById(securityId: Long?): Security?

}
