package com.khrustalev.repairservice.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@FeignClient(name = "repair-parts-service", url = "http://host.docker.internal:8182/api/rest/api/repair-parts-service")
interface RepairPartsServiceFeignClient {
    @PostMapping("/install-parts-to-car")
    fun installPartToCar(@RequestBody partNumberList: List<UUID>,
                         @RequestParam carId: Long) : MutableList<Long>
}