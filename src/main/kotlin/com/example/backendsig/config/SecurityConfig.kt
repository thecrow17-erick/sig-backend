package com.example.backendsig.config

import com.example.backendsig.common.security.JwtFilterSecurity
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val jwtAuthenticationFilter: JwtFilterSecurity
){
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf{it.disable()}
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests{
                req ->
                    req
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
            }
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter::class.java
            );

        return http.build();
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        // Puedes especificar la "fuerza" del algoritmo (4-31, predeterminado es 10)
        return BCryptPasswordEncoder(12)
    }
}