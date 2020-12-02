package com.frankito.presentation.features.pokemondetails

import androidx.lifecycle.viewModelScope
import com.frankito.domain.error.ErrorHandler
import com.frankito.domain.repositories.PokemonRepository
import com.frankito.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(
    private val pokemonRepository: PokemonRepository,
    private val errorHandler: ErrorHandler,
) : BaseViewModel() {

    private val intentFlow = MutableSharedFlow<PokemonDetailIntents>(extraBufferCapacity = 64)

    private val stateFlow = MutableStateFlow(PokemonDetailViewState(true, null))

    val pokemonDetailViewState: StateFlow<PokemonDetailViewState>
        get() = stateFlow

    suspend fun processIntent(intent: PokemonDetailIntents) = intentFlow.emit(intent)

    fun bindIntents() {
        viewModelScope.launch {
            intentFlow.collect { intent ->
                when (intent) {
                    is PokemonDetailIntents.FetchPokemon -> {
                        fetchPokemon(intent.name)
                    }
                }
            }
        }
    }

    fun fetchPokemon(pokemonName: String) {
        stateFlow.value = stateFlow.value.copy(isLoading = true)
        viewModelScope.launch(errorHandler) {
            val pokemonDetail = pokemonRepository.fetchPokemon(pokemonName)
            stateFlow.value = stateFlow.value.copy(pokemonDetail = pokemonDetail)
        }.invokeOnCompletion {
            stateFlow.value = stateFlow.value.copy(isLoading = false)
        }
    }
}