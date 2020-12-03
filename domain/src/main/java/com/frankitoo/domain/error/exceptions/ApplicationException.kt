package com.frankitoo.domain.error.exceptions

open class ApplicationException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)
