package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.storage.RepairRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface RepairRequestRepository : JpaRepository<RepairRequest, Long> {

    fun findAllByCar_Number(carNumber: String) : MutableList<RepairRequest>

    @Query(value = "select rr from RepairRequest rr where rr.car.number = :carNumber and rr.createDate > :actualDate")
    fun getActual(@Param("actualDate") actualDate: LocalDateTime, @Param("carNumber") carNumber: String) : MutableList<RepairRequest>
    fun findByRequestNumber(requestNumber: Long) : RepairRequest
}