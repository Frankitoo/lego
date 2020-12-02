package com.frankito.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.frankito.domain.error.ErrorHandler
import com.frankito.domain.models.pokemon.samples.Pokemons
import com.frankito.domain.repositories.PokemonRepository
import com.frankito.presentation.features.pokemondetails.PokemonDetailViewState
import com.frankito.presentation.features.pokemondetails.PokemonDetailsViewModel
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PokemonDetailsViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val testExceptionHandler = TestCoroutineExceptionHandler()

    private val errorHandler =
        object : ErrorHandler, CoroutineExceptionHandler by testExceptionHandler {}

    @RelaxedMockK
    lateinit var pokemonRepository: PokemonRepository

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        clearAllMocks()
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `Fetch pokemon details should start loading, fetch data and stop loading`() {
        //given
        val model = createModel()

        val collector = mockCollector<PokemonDetailViewState>()

        coEvery { pokemonRepository.fetchPokemon(any()) } returns Pokemons.pokemonDetailModel

        //when
        testDispatcher.runBlockingTest {
            val job = launch {
                model.pokemonDetailViewState.collect { collector.emit(it) }
            }
            model.fetchPokemon("balbasaur")
            advanceUntilIdle()
            job.cancel()
        }

        //then
        coVerifyOrder {
            collector.emit(PokemonDetailViewState(true, null))
            collector.emit(PokemonDetailViewState(true, Pokemons.pokemonDetailModel))
            collector.emit(PokemonDetailViewState(false, Pokemons.pokemonDetailModel))
        }
    }

    private fun createModel(): PokemonDetailsViewModel = PokemonDetailsViewModel(
        pokemonRepository, errorHandler
    )
}