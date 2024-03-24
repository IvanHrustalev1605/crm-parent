//package com.khrustalev.storageservice.elasticsearch.documents
//
//import jakarta.persistence.Column
//import jakarta.persistence.Id
//import org.springframework.data.elasticsearch.annotations.Document
//import org.springframework.data.elasticsearch.annotations.Field
//import org.springframework.data.elasticsearch.annotations.FieldType
//import java.time.LocalDateTime
//import java.util.*
//
//@Document(indexName = "repair_parts")
//open class RepairParts {
//    @Id
//    open var id: Long? = null
//    @Field(type = FieldType.Text, name = "number")
//    open var number: UUID = UUID.randomUUID()
//    @Field(type = FieldType.Text, name = "name")
//    open var name: String? = null
//    @Field(type = FieldType.Long, name = "mileageResource")
//    open var mileageResource: Long? = null
//    @Field(type = FieldType.Date, name = "installedAt")
//    open var installedAt: LocalDateTime? = null
//    @Field(type = FieldType.Boolean, name = "isOrigin")
//    open var isOrigin: Boolean? = null
//    @Field(type = FieldType.Boolean, name = "installed")
//    open var installed: Boolean = false
//    @Field(type = FieldType.Text, name = "vendorArt")
//    open var vendorArt: String = ""
//    @Field(type = FieldType.Long, name = "repairPartsLargeGroupId")
//    val repairPartsLargeGroupId: Long? = null
//    @Field(type = FieldType.Long, name = "etalonPartsDictionaryId")
//    val etalonPartsDictionaryId: Long? = null
//    @Field(type = FieldType.Long, name = "carId")
//    val carId: Long? = null
//}