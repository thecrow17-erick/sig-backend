package com.example.backendsig.distribuitor.service

import com.example.backendsig.auth.dto.LoginDto
import com.example.backendsig.common.service.Bcrypt
import com.example.backendsig.distribuitor.dto.CreateDistribuitorDto
import com.example.backendsig.distribuitor.entity.Distribuitor
import com.example.backendsig.distribuitor.repository.DistribuitorRepository
import com.example.backendsig.error.exception.BadRequestException
import com.example.backendsig.error.exception.InternalServerErrorException
import com.example.backendsig.error.exception.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class DistribuitorService (
    private val distribuitorRepository: DistribuitorRepository,
    private val bCrytpt: Bcrypt
) {

    fun findEmail(loginDto: LoginDto): Optional<Distribuitor> {
        try {
            val findDisEmail = this.distribuitorRepository.findFirstByEmail(loginDto.email);
            return findDisEmail;
        }catch (e: Exception){
            throw InternalServerErrorException(e.message.toString());
        }
    }

    fun findAllDistribuitos(page: Int, size: Int): Page<Distribuitor> {
        val pageable = PageRequest.of(page, size);
        return this.distribuitorRepository.findAllBy(pageable);
    }

    fun createDistribuitor(createDistribuitorDto: CreateDistribuitorDto): Distribuitor {
        try {
            val findEmailOrCellphone = this.distribuitorRepository.findFirstByCelphoneOrEmail(createDistribuitorDto.celphone,createDistribuitorDto.email);
            if(findEmailOrCellphone.isPresent){
                throw BadRequestException("Este email o numero de telefono ya pertenece a un distribuidor");
            }
            val createDistribuitor = Distribuitor(
                name = createDistribuitorDto.name,
                email = createDistribuitorDto.email,
                password = this.bCrytpt.hashPass(
                    createDistribuitorDto.name.get(0) + "." + createDistribuitorDto.celphone
                ),
                capacity = createDistribuitorDto.capacity,
                celphone = createDistribuitorDto.celphone,
                type_veh = createDistribuitorDto.type_veh
            );
            return this.distribuitorRepository.save(createDistribuitor);
        }catch (e: Exception){
            throw InternalServerErrorException(e.message.toString());
        }
    }

    fun updateDistribuitor(id: String, createDistribuitorDto: CreateDistribuitorDto): Distribuitor {
        try{
            val findDistribuitorId = this.distribuitorRepository.findDistribuitorById(id);
            if(findDistribuitorId.isEmpty){
                throw NotFoundException("El distribuidor no se encuentra en el sistema");
            }
            val findDistribuidor = this.distribuitorRepository.findFirstByEmailOrCelphoneAndIdNot(createDistribuitorDto.email,createDistribuitorDto.celphone,id);
            if(findDistribuidor.isPresent){
                throw BadRequestException("Ya existen un email o numero telefonico en uso");
            }
            val updateDistribuitor = findDistribuitorId.get().copy(
                email = createDistribuitorDto.email,
                capacity = createDistribuitorDto.capacity,
                celphone = createDistribuitorDto.celphone,
                type_veh = createDistribuitorDto.type_veh,
                name = createDistribuitorDto.name,
            );
            return this.distribuitorRepository.save(updateDistribuitor);
        }catch (e: Exception){
            throw InternalServerErrorException(e.message.toString());
        }
    }

    fun deleteDistribuitorById(id: String): Distribuitor {
        try{
            val findDistribuitorId = this.distribuitorRepository.findFirstByIdAndStatus(id, true);
            if(findDistribuitorId.isEmpty){
                throw NotFoundException("El distribuidor no se encuentra en el sistema o ya esta de baja");
            }
            val updateDistribuitor = findDistribuitorId.get().copy(
                status = false
            );
            return updateDistribuitor;
        }catch (e:Exception){
            throw InternalServerErrorException(e.message.toString());
        }
    }

}