package com.frankito.presentation.features.pokemondetails

import com.frankito.domain.models.pokemon.PokemonDetail

data class PokemonDetailViewState(
    val isLoading: Boolean,
    val pokemonDetail: PokemonDetail? = null
)