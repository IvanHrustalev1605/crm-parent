package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.storage.CarArrivalState
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CarArrivalStateRepository : JpaRepository<CarArrivalState, Long> {
    fun findFirstByCar_NumberAndInBaseTrueOrderByArrivalTimeDesc(carNumber: String) : CarArrivalState

    @Query(value = "select cas from CarArrivalState cas where cas.needRepair = true " +
            "and cas.repairRequestWritten = false")
    fun findAllWithoutRepairRequests() : MutableList<CarArrivalState>
    fun findFirstByCar_IdAndInBaseTrue(carId: Long) : CarArrivalState
}