package com.frankitoo.presentation.features.characterdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frankitoo.presentation.R
import kotlinx.android.synthetic.main.item_ability.view.tvAbility

class SkillsAdapter(private val skills: List<String>) :
    RecyclerView.Adapter<SkillsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_skill, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        skills[position].let { holder.bindView(it) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvAbility: TextView = itemView.tvAbility

        fun bindView(skill: String) {
            tvAbility.text = skill
        }
    }

    override fun getItemCount(): Int = skills.size
}
