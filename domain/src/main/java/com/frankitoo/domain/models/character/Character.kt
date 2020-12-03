package com.frankitoo.domain.models.character

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey
    val id: Int,
    val name: String? = null,
    val attack: String? = null,
    val defense: String? = null,
    val speed: String? = null,
    val special: String? = null,
    val skills: List<String> = emptyList(),
    val imageUrl: String? = null,
)
