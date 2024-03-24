//package com.khrustalev.storageservice.elasticsearch.service.impl
//
//import com.khrustalev.storageservice.elasticsearch.documents.RepairParts
//import com.khrustalev.storageservice.elasticsearch.elk_repositorys.RepairPartsDocumentRepository
//import com.khrustalev.storageservice.elasticsearch.service.ElasticRepairPartsService
//import org.springframework.stereotype.Service
//
//@Service
//class ElasticRepairPartsServiceImpl(private var repairPartsDocumentRepository: RepairPartsDocumentRepository) : ElasticRepairPartsService {
//    override fun getByNumber(number: String): MutableList<RepairParts> {
//        return repairPartsDocumentRepository.findAllByNumberContainingIgnoreCase(number)
//    }
//
//    override fun getAllPartsNumbers(): MutableList<RepairParts> {
//        return repairPartsDocumentRepository.findAll().toMutableList()
//    }
//}