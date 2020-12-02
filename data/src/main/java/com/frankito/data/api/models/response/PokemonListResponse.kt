package com.frankito.data.api.models.response

import com.squareup.moshi.Json

data class PokemonListResponse(
    @Json(name = "count") val count: Int,
    @Json(name = "next") val next: String?,
    @Json(name = "previous") val previous: String?,
    @Json(name = "results") val results: List<PokemonListItemResponse>
)