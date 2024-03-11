package org.khrustalev.documentservice.dto

class CarRepairStateDto(val id: Long? = null,
                        val application: String? = null,
                        val repairParts: List<Long>? = null,
                        val repairProblems: String? = null,
                        val carId: Long? = null,
                        val mechanicIds: MutableList<Long>?,
                        val engineerId: Long? = null) {
}