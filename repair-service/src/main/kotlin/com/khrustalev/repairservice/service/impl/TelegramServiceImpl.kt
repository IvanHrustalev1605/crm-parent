package com.khrustalev.repairservice.service.impl

import com.khrustalev.repairservice.feign.TelegramFeignClient
import com.khrustalev.repairservice.service.TelegramService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TelegramServiceImpl(private val telegramFeignClient: TelegramFeignClient) : TelegramService {

    @Value("\${bot_chat_id}")
    val CHAT_ID: String = ""
    @Value("\${bot_token}")
    val BOT_TOKEN: String = ""

    override fun sendMessage(text: String): Boolean {
        telegramFeignClient.sendMessage(BOT_TOKEN, text, CHAT_ID)
        return true
    }
}