package com.frankito.data.samples

import com.frankito.data.api.models.response.PokemonDetailResponse
import com.frankito.data.api.models.response.PokemonListItemResponse
import com.frankito.data.api.models.response.PokemonListResponse
import com.frankito.domain.models.pokemon.PokemonListItem

object Pokemons {
    val pokemonListApiModel = PokemonListResponse(
        count = 20,
        next = "",
        previous = "",
        results = listOf(
            PokemonListItemResponse(
                name = "balbasaur",
                url = "https://pokeapi.co/api/v2/pokemon/1/"
            )
        )
    )

    val pokemonListDomainModel = listOf(
        PokemonListItem(
            id = 1,
            name = "balbasaur",
            imageUrl = "https://pokeres.bastionbot.org/images/pokemon/1.png"
        )
    )

    val pokemonDetailApiModel = PokemonDetailResponse(
        id = 1,
        name = "balbasaur",
        height = 0,
        weight = 0,
        experience = 0,
        types = emptyList(),
        abilities = emptyList(),
    )
}