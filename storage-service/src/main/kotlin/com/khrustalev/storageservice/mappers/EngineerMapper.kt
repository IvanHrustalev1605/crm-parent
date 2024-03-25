package com.khrustalev.storageservice.mappers

import com.khrustalev.storageservice.dto.EngineerDto
import com.khrustalev.storageservice.entity.schems.storage.Engineer
import org.springframework.stereotype.Component

@Component
class EngineerMapper {
    fun toDto(entity: Engineer) : EngineerDto = EngineerDto(
        id = entity.id,
        personInfo = entity.personInfo!!,
        employeePosition = entity.employeePosition
    )
    fun toEntity(dto: EngineerDto) : Engineer = Engineer().also {
        it.id = dto.id
        it.employeePosition = dto.employeePosition!!
        it.personInfo = dto.personInfo
    }
}
