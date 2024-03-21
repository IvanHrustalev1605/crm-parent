package com.khrustalev.repairservice.dto.enums

enum class RepairProcessState(id: Int) {
    NEW(0),
    IN_REPAIR(1),
    PROBLEMS(2),
    DONE(3)
}