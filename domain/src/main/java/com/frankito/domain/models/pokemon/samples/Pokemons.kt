package com.frankito.domain.models.pokemon.samples

import com.frankito.domain.models.pokemon.DoubleWithUnit
import com.frankito.domain.models.pokemon.IntWithUnit
import com.frankito.domain.models.pokemon.PokemonDetail
import com.frankito.domain.models.pokemon.PokemonStatUnits

object Pokemons {
    val pokemonDetailModel = PokemonDetail(
        id = 1,
        name = "balbasaur",
        height = DoubleWithUnit(13.4, PokemonStatUnits.heightUnit),
        weight = IntWithUnit(12, PokemonStatUnits.weightUnit),
        experience = IntWithUnit(134, PokemonStatUnits.expUnit),
        imageUrl = "imageUrl",
        types = emptyList(),
        abilities = emptyList(),
    )
}