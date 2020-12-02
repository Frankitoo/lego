package com.frankito.presentation.features.pokemonpager

import com.frankito.domain.models.pokemon.PokemonListItem

data class PokemonPagerViewState(
    val currentItem: Int,
    val selectedPokemon: PokemonListItem? = null,
)