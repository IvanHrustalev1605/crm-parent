package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.schems.dictionary.EtalonPartsDictionary
import com.khrustalev.storageservice.repository.EtalonPartsDictionaryRepository
import com.khrustalev.storageservice.service.abstracts.EtalonPartsDictionaryService
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class EtalonPartsDictionaryServiceImpl(private val etalonPartsDictionaryRepository: EtalonPartsDictionaryRepository) : EtalonPartsDictionaryService {
    override fun findById(etalonPartsDictionaryId: Long): EtalonPartsDictionary? {
        return etalonPartsDictionaryRepository.findById(etalonPartsDictionaryId).orElse(null)
    }
}