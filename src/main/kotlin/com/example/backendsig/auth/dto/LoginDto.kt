package com.example.backendsig.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull

data class LoginDto(
    @field:NotNull
    @field:Email
    val email: String,
    @field:NotNull
    val password: String
)
