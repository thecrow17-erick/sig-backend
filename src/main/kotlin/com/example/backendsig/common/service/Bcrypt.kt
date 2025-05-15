package com.example.backendsig.common.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class Bcrypt(
    private val passwordEncoder: PasswordEncoder
){
    fun hashPass(pass: String): String {
        return this.passwordEncoder.encode(pass)
    }

    fun verifyPass(rawPass: String , pass: String): Boolean {
        return this.passwordEncoder.matches(rawPass, pass);
    }
}