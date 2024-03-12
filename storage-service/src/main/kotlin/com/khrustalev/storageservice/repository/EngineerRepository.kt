package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.storage.Engineer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface EngineerRepository : JpaRepository<Engineer, Long> {
    @Query(value = "select e.id from Engineer e where " +
            "e.personInfo.firstName like :name or " +
            "e.personInfo.lastName like :name or " +
            "e.personInfo.middleName like :name")
    fun customFindByName(@Param("name")name: String) : Long
}