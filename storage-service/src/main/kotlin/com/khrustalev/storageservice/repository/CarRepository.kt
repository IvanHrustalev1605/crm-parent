package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.dto.CarArrivalStateDto
import com.khrustalev.storageservice.entity.schems.storage.Car
import com.khrustalev.storageservice.entity.schems.storage.CarArrivalState
import com.khrustalev.storageservice.entity.schems.storage.Repair
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface CarRepository : JpaRepository<Car, Long> {

    @Query(value = "select c.id from car c where c.number like %:carNumber%")
    fun customFindCarIdByNumber(@Param("carNumber") carNumber: String) : Long

    fun findByVinNumber(vinNumber: String) : Optional<Car>

    @Query(value = "select r.car from Repair r where r.actual = true")
    fun getCarInRepair() : MutableList<Car>

    @Query(value = "select c.car from CarArrivalState c where c.inBase = true")
    fun getCarsInBase() : MutableList<Car>

    fun findByNumber(number: String) : Optional<Car>
    fun deleteByNumber(number: String) : Boolean
    fun deleteByVinNumber(vinNumber: String) : Boolean

    @Query(value = "select r from Repair r where r.car.id =:carId order by r.endRepair ASC")
    fun getAllCarRepairs(@Param("carId") carId: Long) : MutableList<Repair>
    @Query(value = "select r from Repair r where r.car.id =:carId and r.actual = true order by r.endRepair ASC")
    fun getActualCarRepairs(@Param("carId") carId: Long) : MutableList<Repair>
    @Query(value = "select cas from CarArrivalState cas where cas.car.id =:carId")
    fun getAllArrivesByCarId(@Param("carId") carId: Long) : MutableList<CarArrivalState>
}