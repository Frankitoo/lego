package com.frankitoo.presentation.features.weaponlist

import androidx.recyclerview.widget.DiffUtil
import com.frankitoo.domain.models.lego.weapon.Weapon

class WeaponDiffUtilCallback : DiffUtil.ItemCallback<Weapon>() {
    override fun areItemsTheSame(oldItem: Weapon, newItem: Weapon): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Weapon, newItem: Weapon): Boolean {
        return oldItem.name == newItem.name && oldItem.imageUrl == newItem.imageUrl
    }
}

