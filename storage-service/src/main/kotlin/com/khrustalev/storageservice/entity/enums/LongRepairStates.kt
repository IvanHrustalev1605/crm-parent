package com.khrustalev.storageservice.entity.enums

enum class LongRepairStates(id: Int) {
    NEW(1),
    IN_PLANNED_LONG_REPAIR(2),
    LONG_REPAIR_PROBLEMS(3),
    LEAVE_OUTSIDE(4),
    WAITING_REPAIR_PARTS(5),
    SUCCESSFUL_END(6)
}
