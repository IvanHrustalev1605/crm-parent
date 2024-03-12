package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.storage.RepairParts
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RepairPartsRepository : JpaRepository<RepairParts, Long> {
    @Query(value = "select count(rp.id) from RepairParts rp where rp.repairPartsGroup.id = :groupId")
    fun customCountStocksParts(@Param("groupId") partGroupId: Long) : Long
}