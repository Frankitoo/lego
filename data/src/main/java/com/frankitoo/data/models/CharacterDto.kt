package com.frankitoo.data.models

data class CharacterDto(
    val id: Int? = null,
    val name: String? = null,
    val attack: String? = null,
    val defense: String? = null,
    val speed: String? = null,
    val special: String? = null,
    val skills: List<String> = emptyList(),
    val imageUrl: String? = null,
)
