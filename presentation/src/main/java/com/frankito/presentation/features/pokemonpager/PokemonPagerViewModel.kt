package com.frankito.presentation.features.pokemonpager

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.frankito.domain.models.pokemon.PokemonListItem
import com.frankito.domain.models.toast.ToastData
import com.frankito.domain.services.BackButtonService
import com.frankito.domain.services.ConnectionService
import com.frankito.domain.services.ToastService
import com.frankito.presentation.R
import com.frankito.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonPagerViewModel(
    private val toastService: ToastService,
    private val connectionService: ConnectionService,
    private val backButtonService: BackButtonService,
    private val applicationContext: Context,
) : BaseViewModel() {

    private val intentFlow = MutableSharedFlow<PokemonPagerIntent>(extraBufferCapacity = 64)

    private val stateFlow = MutableStateFlow(PokemonPagerViewState(0))

    val pagerViewState: StateFlow<PokemonPagerViewState>
        get() = stateFlow

    suspend fun processIntent(intent: PokemonPagerIntent) = intentFlow.emit(intent)

    fun bindIntents() {
        viewModelScope.launch {
            intentFlow.collect { intent ->
                when (intent) {
                    is PokemonPagerIntent.PageChanged -> {
                        onPageChanged(intent.position)
                    }
                    is PokemonPagerIntent.PokemonSelected -> {
                        onPokemonSelected(intent.name)
                    }
                }
            }
        }
    }

    private fun onPageChanged(position: Int) {
        stateFlow.value =
            stateFlow.value.copy(currentItem = position)
        if (position == 0) {
            backButtonService.invisible()
        } else {
            backButtonService.visible()
        }
    }

    private fun onPokemonSelected(pokemonListItem: PokemonListItem) {
        if (connectionService.isConnected()) {
            stateFlow.value =
                stateFlow.value.copy(selectedPokemon = pokemonListItem, currentItem = 1)
        } else {
            toastService.showToast(
                ToastData.ofContent(
                    applicationContext.getString(R.string.common_network_unavailable)
                )
            )
        }
    }
}