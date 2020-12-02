package com.frankito.presentation.features.pokemondetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frankito.presentation.R
import kotlinx.android.synthetic.main.item_ability.view.*


class AbilityAdapter(private val abilities: List<String>) :
    RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ability, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        abilities[position].let { holder.bindView(it) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvAbility: TextView = itemView.tvAbility

        fun bindView(ability: String) {
            tvAbility.text = ability
        }
    }

    override fun getItemCount(): Int = abilities.size
}