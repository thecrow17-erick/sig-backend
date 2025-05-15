package com.example.backendsig.common.model

data class TokenResponse(
    val userId: String? = null,
    val isExpired: Boolean
)
