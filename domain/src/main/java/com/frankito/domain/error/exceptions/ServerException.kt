package com.frankito.domain.error.exceptions

open class ServerException(
    val status: Int,
    val errorCode: String,
    message: String?,
    cause: Throwable? = null
) : ApplicationException(message, cause)