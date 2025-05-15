package com.example.backendsig.common.service

import com.example.backendsig.common.model.TokenResponse
import com.example.backendsig.config.JwtConfig
import com.example.backendsig.error.exception.UnauthorizedException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date

@Service
class JwtService(
    private val jwtConfig: JwtConfig
) {

    fun generateToken(userId: String): String {
        val now = Instant.now();
        val expiredToken = now.plus(jwtConfig.getExpirationTime(), ChronoUnit.HOURS);

        return Jwts.builder()
            .setSubject(userId)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(expiredToken))
            .signWith(jwtConfig.secretKey(), SignatureAlgorithm.HS256)
        .compact();
    }

    fun decoderToken(token: String): TokenResponse {
        return try {
            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(jwtConfig.secretKey())
                .build()
                .parseClaimsJws(token)
                .body

            TokenResponse(
                userId = claims.subject,
                isExpired = false
            )
        } catch (ex: ExpiredJwtException) {
            TokenResponse(
                userId = null,
                isExpired = true
            )
        } catch (ex: Exception) {
            throw RuntimeException("Token inválido", ex)
        }
    }
}