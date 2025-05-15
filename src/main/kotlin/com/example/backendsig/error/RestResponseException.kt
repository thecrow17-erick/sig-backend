package com.example.backendsig.error

import com.example.backendsig.error.exception.BadRequestException
import com.example.backendsig.error.exception.NotFoundException
import com.example.backendsig.error.exception.UnauthorizedException
import com.example.backendsig.error.model.ErrorModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpServerErrorException.InternalServerError


@RestControllerAdvice
class RestResponseException {

    @ExceptionHandler(InternalServerError::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalServerError(ex: InternalServerError): ErrorModel {
        val errorMessage = ex.message.toString();
        return ErrorModel(
            message = listOf(errorMessage),
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ErrorModel {
        val messages = ex.allErrors.stream().map { err: ObjectError -> err.defaultMessage.toString() }.toList()
        val errorMessage: ErrorModel = ErrorModel(
            statusCode =  HttpStatus.BAD_REQUEST.value(),
            message = messages,
            error = HttpStatus.BAD_REQUEST
        )
        return errorMessage
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFoundException(ex: NotFoundException): ErrorModel {
        val messages: List<String> = listOf<String>(ex.message.toString())
        val errorMessage: ErrorModel = ErrorModel(
            statusCode =  HttpStatus.NOT_FOUND.value(),
            message = messages,
            error = HttpStatus.NOT_FOUND
        )

        return errorMessage
    }


    @ExceptionHandler(UnauthorizedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleResourceUnauthorizedException(ex: UnauthorizedException): ErrorModel {
        val messages = listOf<String>(ex.message.toString())
        val errorMessage: ErrorModel = ErrorModel(
            statusCode = HttpStatus.UNAUTHORIZED.value(),
            message = messages,
            error = HttpStatus.UNAUTHORIZED
        )
        return errorMessage
    }

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleResourceBadRequestException(ex: BadRequestException): ErrorModel {
        val messages: List<String> = listOf(ex.message.toString())
        val errorMessage: ErrorModel = ErrorModel(
            statusCode = HttpStatus.BAD_REQUEST.value(),
            message = messages,
            error = HttpStatus.BAD_REQUEST
        )
        return errorMessage
    }

}