package com.frankito.presentation.features.pokemonlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frankito.presentation.R
import com.frankito.presentation.utils.startRotatedAnimation
import kotlinx.android.synthetic.main.item_loading_state.view.*

class PokemonLoadingAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PokemonLoadingAdapter.LoadingStateViewHolder>() {

    class LoadingStateViewHolder(itemView: View, retry: () -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val tvErrorMessage: TextView = itemView.tvErrorMessage
        private val pokeBallLoaderImage: ImageView = itemView.pokeballLoaderImage
        private val btnRetry: Button = itemView.btnRetry

        init {
            btnRetry.setOnClickListener {
                retry.invoke()
            }
        }


        fun bindState(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                tvErrorMessage.text = loadState.error.localizedMessage
            }
            if (loadState is LoadState.Loading) {
                pokeBallLoaderImage.isVisible = true
                pokeBallLoaderImage.startRotatedAnimation(itemView.context)
            }
            tvErrorMessage.isVisible = loadState !is LoadState.Loading
            btnRetry.isVisible = loadState !is LoadState.Loading
        }

    }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_loading_state, parent, false)
        return LoadingStateViewHolder(view, retry)
    }
}