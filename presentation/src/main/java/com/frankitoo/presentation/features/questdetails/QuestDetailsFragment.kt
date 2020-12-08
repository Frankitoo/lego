package com.frankitoo.presentation.features.questdetails

import android.view.View
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.frankitoo.domain.models.lego.quest.Quest
import com.frankitoo.presentation.R
import com.frankitoo.presentation.base.BaseFragment
import com.frankitoo.presentation.utils.fadeOut
import com.frankitoo.presentation.utils.getCircularProgressDrawable
import com.frankitoo.presentation.utils.startRotatedAnimation
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_quest_details.descriptionCard
import kotlinx.android.synthetic.main.fragment_quest_details.imageView
import kotlinx.android.synthetic.main.fragment_quest_details.loaderImage
import kotlinx.android.synthetic.main.fragment_quest_details.loaderLayout
import kotlinx.android.synthetic.main.fragment_quest_details.tvDescription
import kotlinx.android.synthetic.main.fragment_quest_details.tvName
import org.koin.android.viewmodel.ext.android.viewModel

class QuestDetailsFragment : BaseFragment<QuestDetailsViewModel>() {

    override val layoutRes: Int = R.layout.fragment_quest_details

    override val viewModel: QuestDetailsViewModel by viewModel()

    override fun setupViews() {
        onLoading(true)
        viewModel.quest.observe(this) {
            onVehicle(it)
            onLoading(false)
        }
    }

    private fun onLoading(isLoading: Boolean) {
        loaderImage.startRotatedAnimation(requireContext())
        if (isLoading) {
            loaderLayout.visibility = View.VISIBLE
            loaderLayout.alpha = 1.0f
        } else {
            loaderLayout.fadeOut()
        }
    }

    private fun onVehicle(quest: Quest) {
        val storageRef = FirebaseStorage.getInstance().reference
        quest.imageUrl?.let {
            storageRef.child(it).downloadUrl.addOnSuccessListener { downloadUrl ->
                val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                Glide.with(requireContext())
                    .load(downloadUrl)
                    .fitCenter()
                    .placeholder(getCircularProgressDrawable(requireContext()))
                    .transition(DrawableTransitionOptions.withCrossFade(factory))
                    .into(imageView)
            }
        }

        setupName(quest)
        setupDescription(quest)
    }

    private fun setupName(quest: Quest) {
        tvName.isVisible = !quest.name.isNullOrEmpty()
        if (quest.name != null) {
            tvName.text = quest.name
        }
    }

    private fun setupDescription(quest: Quest) {
        descriptionCard.isVisible = !quest.description.isNullOrEmpty()
        if (quest.description != null) {
            tvDescription.text = quest.description
        }
    }
}



