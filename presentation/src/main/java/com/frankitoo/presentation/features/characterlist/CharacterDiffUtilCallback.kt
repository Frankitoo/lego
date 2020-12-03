package com.frankitoo.presentation.features.characterlist

import androidx.recyclerview.widget.DiffUtil
import com.frankitoo.domain.models.character.Character

class CharacterDiffUtilCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name && oldItem.imageUrl == newItem.imageUrl
    }
}
