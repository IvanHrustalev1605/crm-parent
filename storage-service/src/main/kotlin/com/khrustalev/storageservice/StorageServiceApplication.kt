package com.khrustalev.storageservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@SpringBootApplication
//@EnableElasticsearchRepositories
class StorageServiceApplication

fun main(args: Array<String>) {
    runApplication<StorageServiceApplication>(*args)
}
