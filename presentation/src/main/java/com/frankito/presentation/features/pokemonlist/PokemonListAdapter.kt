package com.frankito.presentation.features.pokemonlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.frankito.domain.models.pokemon.PokemonListItem
import com.frankito.presentation.R
import com.frankito.presentation.features.pokemonlist.PokemonListFragment.Companion.SPAN_COUNT
import com.frankito.presentation.utils.WindowHelper
import com.frankito.presentation.utils.debounce.setOnClickListener
import kotlinx.android.synthetic.main.item_pokemon.view.*


class PokemonListAdapter :
    PagingDataAdapter<PokemonListItem, PokemonListAdapter.PokemonViewHolder>(
        PokemonListDiffUtilCallback()
    ) {
    var onClickListener: ((PokemonListItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)

        val dp8 = WindowHelper.pxFromDp(parent.context, 8)
        val itemWidth = WindowHelper.getItemWidthInColumns(parent.context, SPAN_COUNT, dp8)

        val lp = view.layoutParams
        lp.width = itemWidth
        lp.height = itemWidth + WindowHelper.pxFromDp(parent.context, 40)

        view.layoutParams = lp
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {

        val item = getItem(position)

        holder.itemView.setOnClickListener(true) {
            item?.let {
                onClickListener?.invoke(item)
            }
        }

        getItem(position)?.let { holder.bindView(it) }
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.tvName
        private val imageView: ImageView = itemView.imageView
        private val dp8 = WindowHelper.pxFromDp(itemView.context, 8)
        private val dp16 = dp8 * 2
        private val itemWidth = WindowHelper.getItemWidthInColumns(
            itemView.context,
            SPAN_COUNT,
            dp8
        )
        private val imageWidth = itemWidth - dp16

        fun bindView(pokemonListItem: PokemonListItem) {
            tvName.text = pokemonListItem.name

            val lp = imageView.layoutParams
            lp.width = imageWidth
            lp.height = imageWidth
            imageView.layoutParams = lp

            val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

            Glide.with(itemView.context)
                .load(pokemonListItem.imageUrl)
                .fitCenter()
                .placeholder(R.drawable.pokeball)
                .transition(withCrossFade(factory))
                .into(imageView)
        }
    }
}