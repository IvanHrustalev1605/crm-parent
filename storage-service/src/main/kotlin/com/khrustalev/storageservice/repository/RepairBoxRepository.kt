package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.storage.RepairBox
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface RepairBoxRepository : JpaRepository<RepairBox, Long> {
    fun findByBoxNumber(boxNumber: Int) : Optional<RepairBox>

    @Query(value = "select rb from RepairBox rb where rb.isFree = true")
    fun getAllFreeBoxes() : MutableList<RepairBox>
}