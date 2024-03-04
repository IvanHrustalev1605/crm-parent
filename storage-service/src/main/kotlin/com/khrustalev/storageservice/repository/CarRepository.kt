package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CarRepository : JpaRepository<Car, Long> {

    @Query(value = "select c.id from car c where c.number like %:carNumber%")
    fun customFindCarByNumber(@Param("carNumber") carNumber: String) : Long
}