package com.khrustalev.storageservice.elasticsearch.service

import com.khrustalev.storageservice.elasticsearch.documents.RepairParts

interface ElasticRepairPartsService {
    fun getByNumber(number: String) : MutableList<RepairParts>
    fun getAllPartsNumbers() : MutableList<RepairParts>
}