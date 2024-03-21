package com.khrustalev.repairservice.dto.enums

enum class RepairState(id: Int) {
    NEW(0),
    TAKE_TO_WORK(1),
    DIAGNOSTICS(2),
    SPARE_PARTS_ORDERING(3),
    REPAIR_PROCESS(4),
    DONE(5),
    UNPLANNED_PROBLEMS(6),
    LONG_TIME_REPAIR(7)
}