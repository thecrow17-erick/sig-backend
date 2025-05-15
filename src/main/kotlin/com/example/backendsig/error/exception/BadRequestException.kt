package com.example.backendsig.error.exception

import java.lang.RuntimeException

class BadRequestException(message: String): RuntimeException(message)