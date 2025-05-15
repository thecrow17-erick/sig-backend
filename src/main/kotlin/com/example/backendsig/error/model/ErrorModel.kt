package com.example.backendsig.error.model

import org.springframework.http.HttpStatus

data class ErrorModel(
    val statusCode: Int,
    val message: List<String>,
    val error: HttpStatus
)
