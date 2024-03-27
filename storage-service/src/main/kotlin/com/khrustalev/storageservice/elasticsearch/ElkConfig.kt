package com.khrustalev.storageservice.elasticsearch

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration

@Configuration
class ElkConfig : ElasticsearchConfiguration() {
    @Value("\${elastic_host}")
    private val ELASTIC_HOST = "";

    override fun clientConfiguration(): ClientConfiguration {
        return ClientConfiguration.builder()
            .connectedTo("$ELASTIC_HOST:9200")
            .build()
    }
}