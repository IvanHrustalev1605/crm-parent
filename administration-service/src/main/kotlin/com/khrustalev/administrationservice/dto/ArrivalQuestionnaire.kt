package com.khrustalev.administrationservice.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

open class ArrivalQuestionnaire {
 @Schema(description = "Номер машины")
 open var carNumber: String? = null
 @Schema(description = "Нужен ли ремонт машине")
 open var needRepair: Boolean? = null
 @Schema(description = "В случа необходимости ремонта, указать имя инженера к которому отправить водителя писать заявку на ремонт")
 open var engineerName: String? = null
 @Schema(description = "Какие то проблемы с машиной")
 open var carDescriptionProblems: String? = null
}