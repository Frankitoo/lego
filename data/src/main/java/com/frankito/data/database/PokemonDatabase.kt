package com.frankito.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.frankito.data.database.dao.PokemonDao
import com.frankito.domain.models.pokemon.PokemonListItem

@Database(entities = [PokemonListItem::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}