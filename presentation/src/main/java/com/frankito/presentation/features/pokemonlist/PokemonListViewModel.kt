package com.frankito.presentation.features.pokemonlist

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.frankito.domain.models.pokemon.PokemonListItem
import com.frankito.domain.repositories.PokemonRepository
import com.frankito.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class PokemonListViewModel(
    private val pokemonRepository: PokemonRepository
) : BaseViewModel() {
    fun fetchPokemons(): Flow<PagingData<PokemonListItem>> {
        return pokemonRepository.fetchPokemons().cachedIn(viewModelScope)
    }
}
