package com.example.backendsig.distribuitor.repository

import com.example.backendsig.distribuitor.entity.Distribuitor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface DistribuitorRepository: MongoRepository<Distribuitor, String>{
    fun findAllBy(pageable: Pageable): Page<Distribuitor>
    fun findFirstByCelphoneOrEmail(celphone: String, email: String): Optional<Distribuitor>
    fun findDistribuitorById(id: String): Optional<Distribuitor>
    fun findFirstByEmailOrCelphoneAndIdNot(email: String, celphone: String, id: String): Optional<Distribuitor>
    fun findFirstByIdAndStatus(id: String, status: Boolean): Optional<Distribuitor>
    fun findFirstByEmail(email: String): Optional<Distribuitor>
}