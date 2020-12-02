package com.frankito.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.frankito.data.api.PokemonApi
import com.frankito.data.api.mappers.toDomainModel
import com.frankito.data.database.dao.PokemonDao
import com.frankito.domain.error.ErrorHandler
import com.frankito.domain.models.pokemon.PokemonDetail
import com.frankito.domain.models.pokemon.PokemonListItem
import com.frankito.domain.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(
    private val pokemonApi: PokemonApi,
    private val pokemonDao: PokemonDao,
    private val errorHandler: ErrorHandler,
) : PokemonRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchPokemons(): Flow<PagingData<PokemonListItem>> {
        return Pager(
            PagingConfig(pageSize = 60, enablePlaceholders = false, prefetchDistance = 6),
            remoteMediator = PokemonRemoteMediator(pokemonApi, pokemonDao, errorHandler),
            pagingSourceFactory = { pokemonDao.getPokemons() }
        ).flow
    }

    override suspend fun fetchPokemon(name: String): PokemonDetail {
        return pokemonApi.fetchPokemonDetails(name).toDomainModel()
    }
}