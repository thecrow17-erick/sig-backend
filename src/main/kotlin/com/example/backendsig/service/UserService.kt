package com.example.backendsig.service

import com.example.backendsig.entity.User
import com.example.backendsig.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository
){

    fun createUser(): User {
        return userRepository.save(User(
            name = "Erick Milton",
            password = "123456"
        ));
    }

}