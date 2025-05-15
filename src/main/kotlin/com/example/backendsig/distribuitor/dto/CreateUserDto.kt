package com.example.backendsig.distribuitor.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import lombok.Data
import org.jetbrains.annotations.NotNull

@Data
data class CreateDistribuitorDto(
    @field:NotNull
    @field:Size(min = 1, max = 50)
    val name: String,
    @field:NotNull
    @field:Email
    @field:Size(min = 1, max = 50)
    val email: String,
    @field:NotNull
    var capacity: Int,
    @field:Pattern(
        regexp = "^[67]\\d{7}$",
        message = "Debe ser un numero valido y debe tener 8 digitos."
    )
    @field:NotNull
    var celphone: String,
    @field:NotNull
    var type_veh: String,
)
