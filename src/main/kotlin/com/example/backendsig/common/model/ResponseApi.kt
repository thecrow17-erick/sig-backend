package com.example.backendsig.common.model

data class ResponseApi<T>(
    val statusCode: Int,
    val message: String,
    val data: T
)
