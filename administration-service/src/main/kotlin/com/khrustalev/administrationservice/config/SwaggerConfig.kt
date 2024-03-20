package com.khrustalev.administrationservice.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.media.Schema
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition
class SwaggerConfig {
    @Bean
    fun openApi() : OpenAPI {
        val takeAwayPartsSchema = Schema<MutableMap<Long, Long>>()
        takeAwayPartsSchema.addProperty("RepairPartID", Schema<Long>())
        takeAwayPartsSchema.addProperty("MechanicID", Schema<Long>())
        return OpenAPI()
            .components(Components().addSchemas("takeAwayPartsSchema", takeAwayPartsSchema))
    }
}