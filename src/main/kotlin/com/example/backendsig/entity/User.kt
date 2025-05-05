package com.example.backendsig.entity

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id;
import java.util.*

@Document(collection = "Users")
data class  User (
    @Id
    val id: String? = null,
    val name: String,
    val password: String
)