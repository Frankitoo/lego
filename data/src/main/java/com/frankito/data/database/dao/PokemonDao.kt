package com.frankito.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.frankito.domain.models.pokemon.PokemonListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM PokemonListItem ORDER BY id ASC")
    fun getPokemons(): PagingSource<Int, PokemonListItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<PokemonListItem>)
}
