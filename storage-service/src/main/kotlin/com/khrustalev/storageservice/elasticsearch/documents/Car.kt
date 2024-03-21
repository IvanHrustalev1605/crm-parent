package com.khrustalev.storageservice.elasticsearch.documents

import jakarta.persistence.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "cars")
open class Car {
    @Id
    open var id: Long? = null
    @Field(type = FieldType.Text, name = "number")
    open var number: String? = null
    @Field(type = FieldType.Text, name = "vinNumber")
    open var vinNumber: String? = null
    @Field(type = FieldType.Integer, name = "mileage")
    open var mileage: Int? = null
}