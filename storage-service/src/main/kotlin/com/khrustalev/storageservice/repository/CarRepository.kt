package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.storage.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface CarRepository : JpaRepository<Car, Long> {

    @Query(value = "select c.id from car c where c.number like %:carNumber%")
    fun customFindCarByNumber(@Param("carNumber") carNumber: String) : Long
    fun findByVinNumber(vinNumber: String) : Optional<Car>
}