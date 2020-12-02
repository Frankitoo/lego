package com.frankito.data.api.models.response

import com.squareup.moshi.Json

data class GeneralErrorApiModel(
    @Json(name = "code") val code: String? = null,
    @Json(name = "message") val message: String? = null
)