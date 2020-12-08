package com.frankitoo.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.frankitoo.domain.models.lego.Item

class ItemDiffUtilCallback<T : Item> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.name == newItem.name && oldItem.imageUrl == newItem.imageUrl
    }
}
