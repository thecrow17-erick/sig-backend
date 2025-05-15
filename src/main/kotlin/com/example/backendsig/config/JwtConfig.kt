package com.example.backendsig.config

import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.security.*

@Configuration
class JwtConfig (
    @Value("\${jwt.secret-key}")
    private val secretKey: String,
    @Value("\${jwt.expiration}")
    private val expiration: Long
){
    @Bean
    fun secretKey(): Key{
        return Keys.hmacShaKeyFor(secretKey.toByteArray());
    }

    fun getExpirationTime(): Long = expiration;
}
