package com.frankito.domain.error.exceptions

class NetworkException(cause: Throwable, message: String? = cause.message) : ApplicationException(message, cause)