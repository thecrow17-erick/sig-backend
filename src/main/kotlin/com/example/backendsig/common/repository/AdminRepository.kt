package com.example.backendsig.common.repository

import com.example.backendsig.common.entity.Admin
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface AdminRepository: MongoRepository<Admin, String> {
    fun findAdminByEmail(email: String): Optional<Admin>
}