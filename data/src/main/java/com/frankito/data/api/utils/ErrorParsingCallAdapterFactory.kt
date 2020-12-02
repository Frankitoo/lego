package com.frankito.data.api.utils

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ErrorParsingCallAdapterFactory private constructor() : CallAdapter.Factory() {
    companion object {
        fun create(): ErrorParsingCallAdapterFactory = ErrorParsingCallAdapterFactory()
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? =
        ErrorParsingCallAdapter(retrofit, retrofit.nextCallAdapter(this, returnType, annotations))

    private inner class ErrorParsingCallAdapter<R, T>(
        private val retrofit: Retrofit,
        private val delegate: CallAdapter<R, T>
    ) : CallAdapter<R, T> by delegate {
        override fun adapt(call: Call<R>): T = delegate.adapt(ErrorParsingCall(retrofit, call))
    }
}