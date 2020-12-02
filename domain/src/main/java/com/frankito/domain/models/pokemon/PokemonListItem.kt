package com.frankito.domain.models.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonListItem(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageUrl: String,
)