package com.frankito.presentation.features.pokemondetails

sealed class PokemonDetailIntents {
    data class FetchPokemon(val name: String) : PokemonDetailIntents()
}