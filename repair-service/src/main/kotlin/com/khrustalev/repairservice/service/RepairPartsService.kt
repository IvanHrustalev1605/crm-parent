package com.khrustalev.repairservice.service

interface RepairPartsService {
    fun install(repairPartsList: MutableList<Long>, carId: Long) : MutableList<Long>
    fun countPartStocks(): MutableMap<String, Long>
}