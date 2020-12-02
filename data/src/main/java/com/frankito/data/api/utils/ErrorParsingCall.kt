package com.frankito.data.api.utils

import com.frankito.data.api.models.response.GeneralErrorApiModel
import com.frankito.domain.error.exceptions.NetworkException
import com.frankito.domain.error.exceptions.ServerException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ErrorParsingCall<T>(
    private val retrofit: Retrofit,
    private val delegate: Call<T>
) : Call<T> by delegate {
    private val errorConverter by lazy {
        retrofit.responseBodyConverter<GeneralErrorApiModel>(GeneralErrorApiModel::class.java, emptyArray())
    }

    override fun execute(): Response<T> = runCatching { delegate.execute() }.fold(
        onSuccess = { it.parseErrorBody() },
        onFailure = { throw NetworkException(it) }
    )

    override fun enqueue(callback: Callback<T>) = delegate.enqueue(object : Callback<T> by callback {
        override fun onResponse(call: Call<T>, response: Response<T>) = try {
            if (response.isSuccessful) {
                callback.onResponse(call, response)
            } else {
                callback.onResponse(call, response.parseErrorBody())
            }
        } catch (e: ServerException) {
            callback.onFailure(call, e)
        }

        override fun onFailure(call: Call<T>, t: Throwable) = callback.onFailure(call, NetworkException(t))
    })

    override fun clone(): Call<T> = ErrorParsingCall(retrofit, delegate.clone())

    @Throws(ServerException::class)
    private fun <T> Response<T>.parseErrorBody(): Response<T> = apply {
        if (!isSuccessful) {
            errorBody()?.let { errorConverter.convert(it) }?.let { errorModel ->
                throw ServerException(code(), errorModel.code ?: "", errorModel.message ?: "")
            }
        }
    }
}