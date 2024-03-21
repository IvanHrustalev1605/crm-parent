package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.dto.CarArrivalStateDto
import com.khrustalev.storageservice.entity.schems.storage.Car
import com.khrustalev.storageservice.entity.schems.storage.CarArrivalState
import com.khrustalev.storageservice.entity.schems.storage.Driver
import com.khrustalev.storageservice.entity.schems.storage.Repair
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DriverRepository : JpaRepository<Driver, Long> {
    fun findByCar_Id(carId: Long) : Driver?

    @Query(value = "select d from driver d where d.personInfo.firstName =:fio or d.personInfo.middleName =:fio or d.personInfo.lastName =:fio")
    fun getDriversByFio(@Param("fio") fio: String) : MutableList<Driver>

    @Query(value = "select c from car c where c.driver.id = :driverId")
    fun getDriversCar(@Param("driverId") driverId: Long) : Car
    @Query(value = "select r from Repair r where r.car.driver.id =:driverId")
    fun getRepairProcessByDriverId(@Param("driverId") driverId: Long) : MutableList<Repair>

    @Query(value = "select cas from CarArrivalState cas where cas.car.driver.id =:driverId")
    fun getAllArrivalsByDriverId(@Param("driverId") driverId: Long) : MutableList<CarArrivalState>

    fun findByLicense(license: String) : Driver
}