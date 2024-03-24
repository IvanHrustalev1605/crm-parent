//package com.khrustalev.storageservice.elasticsearch.service.impl
//
//import com.khrustalev.storageservice.elasticsearch.documents.Car
//import com.khrustalev.storageservice.elasticsearch.elk_repositorys.CarDocumentRepository
//import com.khrustalev.storageservice.elasticsearch.service.ElasticCarService
//import org.springframework.stereotype.Service
//
//@Service
//class ElasticCarServiceImpl(private val carDocumentRepository: CarDocumentRepository) : ElasticCarService {
//    override fun findByNumber(number: String): MutableList<Car> {
//        return carDocumentRepository.findAllByNumberContainingIgnoreCase(number)
//    }
//}