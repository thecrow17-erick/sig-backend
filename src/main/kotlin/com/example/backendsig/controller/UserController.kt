package com.example.backendsig.controller

import com.example.backendsig.entity.User
import com.example.backendsig.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserController (
    private val userService: UserService
){
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(): User {
        return this.userService.createUser();
    }
}