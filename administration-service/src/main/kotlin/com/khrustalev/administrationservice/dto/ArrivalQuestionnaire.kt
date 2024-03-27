package com.khrustalev.administrationservice.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

open class ArrivalQuestionnaire {
 @Schema(description = "ID машины")
 open var carId: Long? = null
 @Schema(description = "Нужен ли ремонт машине")
 open var needRepair: Boolean? = null
 @Schema(description = "В случа необходимости ремонта, ID инженера, к которому будет отправлен водитель писать заявку на ремонт")
 open var engineerId: Long? = null
 @Schema(description = "Какие то проблемы с машиной")
 open var carDescriptionProblems: String? = null
 @Schema(description = "Нужен ли длительный ремонт")
 open var needLongRepair: Boolean? = null

}