package com.example.backendsig.utils

import com.example.backendsig.common.entity.Admin
import com.example.backendsig.common.repository.AdminRepository
import com.example.backendsig.common.service.Bcrypt
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class Runner(
    private val adminRepository: AdminRepository,
    private val bCrytpt: Bcrypt
): CommandLineRunner {
    private val adminEmail: String = "admin@gmail.com"

    override fun run(vararg args: String?) {
        this.createAdmin();
    }

    fun createAdmin(){
        val findAdmin = adminRepository.findAdminByEmail(this.adminEmail);
        if(findAdmin.isPresent){
            return;
        }
        val createAdmin = Admin(
            email = this.adminEmail,
            password = bCrytpt.hashPass("123456")
        );
        this.adminRepository.save(createAdmin);
    }
}