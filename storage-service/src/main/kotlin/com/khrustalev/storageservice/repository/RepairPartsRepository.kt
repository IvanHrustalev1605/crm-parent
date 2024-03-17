package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.dto.RepairPartsDto
import com.khrustalev.storageservice.entity.schems.storage.RepairParts
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RepairPartsRepository : JpaRepository<RepairParts, Long> {
    fun findByNumber(number: UUID) : Optional<RepairParts>
    fun findAllByRepairPartsLargeGroup_Name(name: String) : MutableList<RepairParts>
    fun countAllByRepairPartsLargeGroup_Id(id: Long) : Long
    fun countAllByRepairPartsLargeGroup_Name(groupName: String) : Long
    fun findAllByCar_Id(carId: Long) : MutableList<RepairParts>
    fun findAllByCar_Number(carNumber: String) : MutableList<RepairParts>
    fun countAllByEtalonPartsDictionary_Art(dicArt: String) : Long
    fun findAllByInstalledIsFalse() : MutableList<RepairParts>
    fun findAllByInstalledIsFalseAndCar_Number(carNumber: String) : MutableList<RepairParts>
    fun findAllByEtalonPartsDictionary_Art(dicArt: String) : MutableList<RepairParts>


}