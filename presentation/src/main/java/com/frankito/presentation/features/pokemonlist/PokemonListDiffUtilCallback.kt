package com.frankito.presentation.features.pokemonlist

import androidx.recyclerview.widget.DiffUtil
import com.frankito.domain.models.pokemon.PokemonListItem

class PokemonListDiffUtilCallback : DiffUtil.ItemCallback<PokemonListItem>() {
    override fun areItemsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem): Boolean {
        return oldItem.id == newItem.id
                && oldItem.name == newItem.name
                && oldItem.imageUrl == newItem.imageUrl
    }
}