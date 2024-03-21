package com.khrustalev.storageservice.elasticsearch.elk_repositorys

import com.khrustalev.storageservice.elasticsearch.documents.Car
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface CarDocumentRepository: ElasticsearchRepository<Car, Long> {

    fun findAllByNumberContainingIgnoreCase(number: String) : MutableList<Car>
}