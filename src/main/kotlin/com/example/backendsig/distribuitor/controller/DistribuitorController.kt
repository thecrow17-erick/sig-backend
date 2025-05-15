package com.example.backendsig.distribuitor.controller

import com.example.backendsig.common.model.ResponseApi
import com.example.backendsig.distribuitor.dto.CreateDistribuitorDto
import com.example.backendsig.distribuitor.entity.Distribuitor
import com.example.backendsig.distribuitor.model.ResponseCreateDistribuitor
import com.example.backendsig.distribuitor.service.DistribuitorService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping( "/distribuitor")
class DistribuitorController(
    private val distribuitorService: DistribuitorService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAllDistribuitor(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
    ): ResponseApi<Page<Distribuitor>> {
        return ResponseApi(
            statusCode = HttpStatus.OK.value(),
            message = "Todos los distribuidores",
            data = this.distribuitorService.findAllDistribuitos(page, size)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDistribuitor(
        @Valid @RequestBody createDistribuitorDto: CreateDistribuitorDto
    ): ResponseApi<ResponseCreateDistribuitor> {
        val distribuitor = this.distribuitorService.createDistribuitor(createDistribuitorDto);
        return ResponseApi(
            statusCode = HttpStatus.CREATED.value(),
            message = "Distribuidor creado",
            data = ResponseCreateDistribuitor(
                distribuitor
            )
        );
    }
}