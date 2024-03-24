//package com.khrustalev.storageservice.controllers
//
//import com.khrustalev.storageservice.elasticsearch.documents.Car
//import com.khrustalev.storageservice.elasticsearch.documents.RepairParts
//import com.khrustalev.storageservice.elasticsearch.service.ElasticCarService
//import com.khrustalev.storageservice.elasticsearch.service.ElasticRepairPartsService
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RequestParam
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//@RequestMapping("/api/storage/elastic")
//class ElasticController(private val elasticCarService: ElasticCarService,
//                        private val elasticRepairPartsService: ElasticRepairPartsService) {
//    @GetMapping
//    fun getByNumber(@RequestParam number: String) : ResponseEntity<MutableList<RepairParts>> {
//        return ResponseEntity(elasticRepairPartsService.getByNumber(number), HttpStatus.OK)
//    }
//}