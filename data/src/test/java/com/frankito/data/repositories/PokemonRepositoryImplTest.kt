package com.frankito.data.repositories

import androidx.paging.PagingData
import com.frankito.data.DataCoroutineRule
import com.frankito.data.api.PokemonApi
import com.frankito.data.api.models.response.PokemonListItemResponse
import com.frankito.data.api.models.response.PokemonListResponse
import com.frankito.data.database.PokemonDatabase
import com.frankito.data.database.dao.PokemonDao
import com.frankito.data.mockCollector
import com.frankito.data.samples.Pokemons
import com.frankito.domain.error.ErrorHandler
import com.frankito.domain.models.pokemon.PokemonListItem
import com.frankito.domain.repositories.PokemonRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonRepositoryImplTest {
    @get:Rule
    val rule = DataCoroutineRule()

    private lateinit var repository: PokemonRepository

    @MockK
    lateinit var pokemonApi: PokemonApi

    @MockK
    lateinit var pokemonDao: PokemonDao

    private val testExceptionHandler = TestCoroutineExceptionHandler()

    private val errorHandler =
        object : ErrorHandler, CoroutineExceptionHandler by testExceptionHandler {}

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = PokemonRepositoryImpl(pokemonApi, pokemonDao, errorHandler)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Check fetch pokemon fetches data from remote api`() {
        //given
        coEvery { pokemonApi.fetchPokemonDetails("balbasaur") } returns Pokemons.pokemonDetailApiModel

        //then
        rule.runBlockingTest {
            repository.fetchPokemon("balbasaur")
        }

        //then
        coVerify {
            pokemonApi.fetchPokemonDetails("balbasaur")
        }
    }
}