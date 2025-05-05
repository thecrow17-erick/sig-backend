package com.example.backendsig.repository

import com.example.backendsig.entity.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User, String> {
    fun findUserByName(name: String): User?
}