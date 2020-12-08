package com.frankitoo.domain.models.lego.character

import com.frankitoo.domain.models.lego.Item

data class Character(
    override var id: String? = null,
    override val name: String? = null,
    val attack: String? = null,
    val defense: String? = null,
    val speed: String? = null,
    val special: String? = null,
    val skills: List<String> = emptyList(),
    override val imageUrl: String? = null,
) : Item
