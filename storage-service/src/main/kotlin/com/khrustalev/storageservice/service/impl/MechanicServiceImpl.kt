package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.Mechanic
import com.khrustalev.storageservice.repository.MechanicRepository
import com.khrustalev.storageservice.service.abstracts.MechanicService
import org.springframework.stereotype.Service

@Service
class MechanicServiceImpl(private val mechanicRepository: MechanicRepository) : MechanicService {

    override fun getByIds(ids: MutableList<Long>): MutableList<Mechanic> {
        return mechanicRepository.findAllById(ids)
    }
}