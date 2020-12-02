package com.frankito.domain.error.exceptions

class UnknownException(message: String? = null, cause: Throwable? = null) : ApplicationException(message, cause)