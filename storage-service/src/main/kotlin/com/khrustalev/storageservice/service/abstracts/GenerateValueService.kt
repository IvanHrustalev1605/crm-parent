package com.khrustalev.storageservice.service.abstracts

interface GenerateValueService {
    fun generateDbValues() : Boolean
    fun generateRepairParts() : Boolean
    fun generateDictionary() : Boolean
    fun generateRandomStocks() : Boolean
}