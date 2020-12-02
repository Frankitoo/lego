package com.frankito.presentation.features.pokemondetails

import com.frankito.domain.models.pokemon.PokemonDetail

data class LegoDetailViewState(
    val isLoading: Boolean,
    val pokemonDetail: PokemonDetail? = null
)
