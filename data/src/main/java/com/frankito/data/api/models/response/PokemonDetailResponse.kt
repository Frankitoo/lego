package com.frankito.data.api.models.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class PokemonDetailResponse(
    @Json(name = "id") @PrimaryKey val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "height") val height: Int,
    @Json(name = "weight") val weight: Int,
    @Json(name = "base_experience") val experience: Int,
    @Json(name = "types") val types: List<TypeResponse>,
    @Json(name = "abilities") val abilities: List<AbilityResponse>
) {
    data class AbilityResponse(
        @Json(name = "ability") val ability: AbilityDetailsResponse,
        @Json(name = "is_hidden") val isHidden: Boolean,
        @Json(name = "slot") val slot: Int,
    )

    data class AbilityDetailsResponse(
        @Json(name = "name") val name: String,
        @Json(name = "url") val url: String,
    )

    data class TypeResponse(
        @Json(name = "slot") val slot: Int,
        @Json(name = "type") val type: Type
    )

    data class Type(
        @Json(name = "name") val name: String
    )
}