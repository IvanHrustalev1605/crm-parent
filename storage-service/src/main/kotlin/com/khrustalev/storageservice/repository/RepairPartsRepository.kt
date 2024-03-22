package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.dto.RepairPartsDto
import com.khrustalev.storageservice.entity.schems.storage.RepairParts
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
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

    @Query(value = "select * from storage.repair_parts rp " +
            "join storage.car_repair_state_repair_parts crsrp ON crsrp.repair_parts_id = rp.id " +
            "join storage.repair_car_repair_state rcrs on rcrs.car_repair_state_id = crsrp.car_repair_state_id " +
            "where rcrs.repair_id =:repairId", nativeQuery = true)
    fun getAllInstalledRepairPArtsInRepair(@Param("repairId") repairId: Long) : MutableList<RepairParts>


}