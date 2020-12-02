package com.frankito.domain.models.character

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey
    val id: Int,
    val name: String,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val special: String,
)
