package com.example.backendsig.distribuitor.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "distribuitors")
data class Distribuitor(
    @Id
    val id: String? = null,
    @Indexed(unique = true)
    var email: String,
    var password: String,
    var capacity: Int,
    var name: String,
    @Indexed(unique = true)
    var celphone: String,
    var type_veh: String,
    var status : Boolean = true,
    @CreatedDate
    val createdAt: Instant = Instant.now(),
    @LastModifiedDate
    val updatedAt: Instant = Instant.now()
)
