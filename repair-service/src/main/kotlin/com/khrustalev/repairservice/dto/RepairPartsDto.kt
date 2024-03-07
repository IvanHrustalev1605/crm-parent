package com.khrustalev.repairservice.dto

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

class RepairPartsDto(var id: Long? = null,
                     var number: UUID = UUID.randomUUID(),
                     var name: String? = null,
                     var mileageResource: Long? = null,
                     var installedAt: LocalDateTime? = null,
                     var installed: Boolean = false,
                     var category: Int? = null,
                     var repairPartGroupId: Long? = null,
                     var carId: Long? = null) : Serializable