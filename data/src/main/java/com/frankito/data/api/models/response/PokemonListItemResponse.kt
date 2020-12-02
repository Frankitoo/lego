package com.frankito.data.api.models.response

import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class PokemonListItemResponse(
    @Json(name = "name") @PrimaryKey val name: String,
    @Json(name = "url") val url: String
)