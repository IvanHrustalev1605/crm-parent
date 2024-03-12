package com.khrustalev.repairservice.dto

import java.time.LocalDateTime

open class ArrivalQuestionnaire {
 open var carNumber: String? = null
 open var needRepair: Boolean? = null
 open var engineerName: String? = null
 open var carDescriptionProblems: String? = null
 open var arrivalTime: LocalDateTime? = null
}