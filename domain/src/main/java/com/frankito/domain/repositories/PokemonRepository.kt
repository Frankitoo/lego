package com.frankito.domain.repositories

import androidx.paging.PagingData
import com.frankito.domain.models.pokemon.PokemonDetail
import com.frankito.domain.models.pokemon.PokemonListItem
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun fetchPokemons() : Flow<PagingData<PokemonListItem>>
    suspend fun fetchPokemon(name: String) : PokemonDetail
}