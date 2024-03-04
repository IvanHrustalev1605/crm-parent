package com.khrustalev.repairservice.service

interface TelegramService {
    fun sendMessage(text: String) : Boolean
}