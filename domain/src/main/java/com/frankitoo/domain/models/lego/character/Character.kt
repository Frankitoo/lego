package com.frankitoo.domain.models.lego.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.frankitoo.domain.models.lego.Item

@Entity
data class Character(
    @PrimaryKey
    override var id: String? = null,
    val name: String? = null,
    val attack: String? = null,
    val defense: String? = null,
    val speed: String? = null,
    val special: String? = null,
    val skills: List<String> = emptyList(),
    val imageUrl: String? = null,
) : Item
