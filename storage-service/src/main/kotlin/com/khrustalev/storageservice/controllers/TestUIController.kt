//package com.khrustalev.storageservice.controllers
//
//import com.khrustalev.storageservice.elasticsearch.service.ElasticRepairPartsService
//import org.springframework.stereotype.Controller
//import org.springframework.ui.Model
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RequestMapping
//
//@Controller
//@RequestMapping("/test/ui")
//class TestUIController(private val elasticRepairPartsService: ElasticRepairPartsService) {
//    @GetMapping("/search")
//    fun search(model: Model) : String {
//        val list = elasticRepairPartsService.getAllPartsNumbers().map { it.name }.toMutableList()
//        model.addAttribute("names", list)
//        return "search"
//    }
//}