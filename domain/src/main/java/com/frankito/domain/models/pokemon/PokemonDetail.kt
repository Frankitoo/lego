package com.frankito.domain.models.pokemon

class PokemonDetail (
    val id: Int,
    val name: String,
    val height: DoubleWithUnit,
    val weight: IntWithUnit,
    val experience: IntWithUnit,
    val imageUrl: String,
    val types: List<String>,
    val abilities: List<String>,
)

data class DoubleWithUnit(val value: Double, val unit: String)
data class IntWithUnit(val value: Int, val unit: String)