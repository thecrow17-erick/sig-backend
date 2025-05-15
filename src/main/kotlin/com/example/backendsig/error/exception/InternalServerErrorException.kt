package com.example.backendsig.error.exception

import java.lang.RuntimeException

class InternalServerErrorException(message: String) : RuntimeException(message)