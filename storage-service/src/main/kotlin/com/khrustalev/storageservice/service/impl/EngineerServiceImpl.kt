package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.schems.storage.Engineer
import com.khrustalev.storageservice.exception.NotFoundEntityException
import com.khrustalev.storageservice.repository.EngineerRepository
import com.khrustalev.storageservice.service.abstracts.EngineerService
import org.springframework.stereotype.Service

@Service
class EngineerServiceImpl(private var engineerRepository: EngineerRepository) : EngineerService {
    override fun findById(id: Long): Engineer {
        return engineerRepository.findById(id).orElseThrow { NotFoundEntityException("Engineer not found by $id") }
    }

    override fun findIdByName(name: String): Long? {
        return engineerRepository.customFindByName(name)
    }
}