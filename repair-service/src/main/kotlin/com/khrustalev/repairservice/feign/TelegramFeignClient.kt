package com.khrustalev.repairservice.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "telegram-feign-client", url = "https://api.telegram.org/")
interface TelegramFeignClient {
    @PostMapping("bot{token}/sendMessage")
    fun sendMessage(@PathVariable token: String,
                    @RequestParam("text") text: String,
                    @RequestParam("chat_id") chatId: String)
}