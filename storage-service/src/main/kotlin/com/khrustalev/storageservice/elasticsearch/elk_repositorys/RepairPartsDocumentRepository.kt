package com.khrustalev.storageservice.elasticsearch.elk_repositorys

import com.khrustalev.storageservice.elasticsearch.documents.RepairParts
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface RepairPartsDocumentRepository : ElasticsearchRepository<RepairParts, Long> {
    fun findAllByNumberContainingIgnoreCase(number: String) : MutableList<RepairParts>
}