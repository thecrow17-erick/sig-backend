package com.example.backendsig.entity

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id;

@Document(collection = "Users")
data class  User (
    @Id
    val id: String,
    val name: String,
    val password: String
)