package com.frankito.data.api.mappers

import com.frankito.data.api.models.response.PokemonDetailResponse
import com.frankito.data.api.models.response.PokemonListItemResponse
import com.frankito.domain.models.pokemon.*

fun PokemonListItemResponse.toDomainModel(): PokemonListItem {
    val pokemonId = getPokemonIdFromUrl(url)
    val imageUrl = getPokemonImageUrlById(pokemonId)
    return PokemonListItem(
        id = pokemonId,
        name = name,
        imageUrl = imageUrl
    )
}

fun PokemonDetailResponse.toDomainModel(): PokemonDetail {
    val imageUrl = getPokemonImageUrlById(id)
    return PokemonDetail(
        id = id,
        name = name,
        // The height and weigh values are in decimeter and hectogram
        height = DoubleWithUnit(height.toDouble() / 10, PokemonStatUnits.heightUnit),
        weight = IntWithUnit(weight / 10, PokemonStatUnits.weightUnit),
        experience = IntWithUnit(experience, PokemonStatUnits.expUnit),
        imageUrl = imageUrl,
        types = types.map { it.type.name },
        abilities = abilities.map { it.ability.name }
    )
}

fun getPokemonIdFromUrl(url: String): Int =
    url.split("/".toRegex()).dropLast(1).last().toInt()

fun getPokemonImageUrlById(id: Int): String {
    return "https://pokeres.bastionbot.org/images/pokemon/$id.png"
}