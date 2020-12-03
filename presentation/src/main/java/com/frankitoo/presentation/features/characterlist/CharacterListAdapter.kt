package com.frankitoo.presentation.features.characterlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.frankitoo.domain.models.character.Character
import com.frankitoo.presentation.R
import com.frankitoo.presentation.features.characterlist.CharacterListFragment.Companion.SPAN_COUNT
import com.frankitoo.presentation.utils.WindowHelper
import com.frankitoo.presentation.utils.debounce.setOnClickListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.item_character.view.imageView
import kotlinx.android.synthetic.main.item_character.view.tvName

class CharacterListAdapter :
    PagingDataAdapter<Character, CharacterListAdapter.CharacterViewHolder>(
        CharacterDiffUtilCallback()
    ) {
    var onClickListener: ((Character) -> Unit)? = null

    // Allows to remember the last item shown on screen
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)

        val dp8 = WindowHelper.pxFromDp(parent.context, 8)
        val itemWidth = WindowHelper.getItemWidthInColumns(parent.context, SPAN_COUNT, dp8)

        val lp = view.layoutParams
        lp.width = itemWidth
        lp.height = itemWidth + WindowHelper.pxFromDp(parent.context, 40)

        view.layoutParams = lp
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        val item = getItem(position)

        holder.itemView.setOnClickListener(true) {
            item?.let {
                onClickListener?.invoke(item)
            }
        }

        getItem(position)?.let { holder.bindView(it) }

        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.slide_from_bottom)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

        fun bindView(character: Character) {
            tvName.text = character.name

            val lp = imageView.layoutParams
            lp.width = imageWidth
            lp.height = imageWidth
            imageView.layoutParams = lp

            val storageRef = FirebaseStorage.getInstance().reference
            character.imageUrl?.let {
                storageRef.child(it).downloadUrl.addOnSuccessListener { downloadUrl ->
                    val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                    Glide.with(itemView.context)
                        .load(downloadUrl)
                        .fitCenter()
                        .placeholder(R.drawable.loader)
                        .transition(withCrossFade(factory))
                        .into(imageView)
                }
            }
        }
    }
}
