package com.frankitoo.domain.models.lego.quest

import com.frankitoo.domain.models.lego.Item

data class Quest(
    override var id: String? = null,
    override val name: String? = null,
    val description: String? = null,
    override val imageUrl: String? = null,
) : Item
