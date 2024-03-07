package com.khrustalev.storageservice.entity.enums

enum class RepairProcessState(id: Int) {
    NEW(0),
    IN_REPAIR(1),
    PROBLEMS(2),
    DONE(3)
}