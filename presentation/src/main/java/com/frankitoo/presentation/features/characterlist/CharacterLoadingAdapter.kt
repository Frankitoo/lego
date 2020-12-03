package com.frankitoo.presentation.features.characterlist

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
import com.frankitoo.presentation.R
import com.frankitoo.presentation.utils.startRotatedAnimation
import kotlinx.android.synthetic.main.item_loading_state.view.btnRetry
import kotlinx.android.synthetic.main.item_loading_state.view.legoHeadLoaderImage
import kotlinx.android.synthetic.main.item_loading_state.view.tvErrorMessage

class CharacterLoadingAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CharacterLoadingAdapter.LoadingStateViewHolder>() {

    class LoadingStateViewHolder(itemView: View, retry: () -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val tvErrorMessage: TextView = itemView.tvErrorMessage
        private val pokeBallLoaderImage: ImageView = itemView.legoHeadLoaderImage
        private val btnRetry: Button = itemView.btnRetry

        init {
            btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bindState(loadState: LoadState) {
            pokeBallLoaderImage.isVisible = false
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
