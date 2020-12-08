package com.frankitoo.domain.models.lego.vehicle

import com.frankitoo.domain.models.lego.Item

data class Vehicle(
    override var id: String? = null,
    override val name: String? = null,
    val attack: String? = null,
    val defense: String? = null,
    val capacity: String? = null,
    val speed: String? = null,
    val special: String? = null,
    override val imageUrl: String? = null,
) : Item
