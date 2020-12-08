package com.frankitoo.presentation.features.weapondetails

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.frankitoo.domain.models.lego.weapon.Weapon
import com.frankitoo.presentation.R
import com.frankitoo.presentation.base.BaseFragment
import com.frankitoo.presentation.features.common.SkillsAdapter
import com.frankitoo.presentation.utils.fadeOut
import com.frankitoo.presentation.utils.getCircularProgressDrawable
import com.frankitoo.presentation.utils.startRotatedAnimation
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_weapon_details.attackLayout
import kotlinx.android.synthetic.main.fragment_weapon_details.defenseLayout
import kotlinx.android.synthetic.main.fragment_weapon_details.imageView
import kotlinx.android.synthetic.main.fragment_weapon_details.loaderImage
import kotlinx.android.synthetic.main.fragment_weapon_details.loaderLayout
import kotlinx.android.synthetic.main.fragment_weapon_details.rvSkills
import kotlinx.android.synthetic.main.fragment_weapon_details.skillsLayout
import kotlinx.android.synthetic.main.fragment_weapon_details.specialCard
import kotlinx.android.synthetic.main.fragment_weapon_details.speedLayout
import kotlinx.android.synthetic.main.fragment_weapon_details.tvAttackValue
import kotlinx.android.synthetic.main.fragment_weapon_details.tvDefenseValue
import kotlinx.android.synthetic.main.fragment_weapon_details.tvName
import kotlinx.android.synthetic.main.fragment_weapon_details.tvSpecial
import kotlinx.android.synthetic.main.fragment_weapon_details.tvSpeedValue
import org.koin.android.viewmodel.ext.android.viewModel

class WeaponDetailsFragment : BaseFragment<WeaponDetailsViewModel>() {

    override val layoutRes: Int = R.layout.fragment_weapon_details

    override val viewModel: WeaponDetailsViewModel by viewModel()

    override fun setupViews() {
        onLoading(true)
        viewModel.weapon.observe(this) {
            onWeapon(it)
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

    private fun onWeapon(weapon: Weapon) {
        val storageRef = FirebaseStorage.getInstance().reference
        weapon.imageUrl?.let {
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

        setupStats(weapon)
        setupSkills(weapon.skills)
        setupSpecial(weapon)
    }

    private fun setupStats(weapon: Weapon) {
        tvName.text = weapon.name
        attackLayout.isVisible = !weapon.attack.isNullOrEmpty()
        if (weapon.attack != null) {
            tvAttackValue.text = weapon.attack.toString()
        }
        defenseLayout.isVisible = !weapon.defense.isNullOrEmpty()
        if (weapon.defense != null) {
            tvDefenseValue.text = weapon.defense.toString()
        }
        speedLayout.isVisible = !weapon.speed.isNullOrEmpty()
        if (weapon.speed != null) {
            tvSpeedValue.text = weapon.speed.toString()
        }
    }

    private fun setupSkills(skills: List<String>) {
        if (skills.isNotEmpty()) {
            rvSkills.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvSkills.adapter = SkillsAdapter(skills)
            skillsLayout.visibility = View.VISIBLE
        } else {
            skillsLayout.visibility = View.GONE
        }
    }

    private fun setupSpecial(weapon: Weapon) {
        specialCard.isVisible = !weapon.special.isNullOrEmpty()
        if (weapon.special != null) {
            tvSpecial.text = weapon.special
        }
    }
}

