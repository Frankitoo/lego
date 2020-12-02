package com.frankito.presentation.features.pokemonpager

import com.frankito.domain.models.pokemon.PokemonListItem

sealed class PokemonPagerIntent {
    data class PageChanged(val position: Int) : PokemonPagerIntent()
    data class PokemonSelected(val name: PokemonListItem) : PokemonPagerIntent()
}