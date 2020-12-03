package com.frankitoo.presentation.features.characterdetails

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.frankitoo.domain.models.character.Character
import com.frankitoo.domain.models.character.Skill
import com.frankitoo.presentation.R
import com.frankitoo.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_character_details.rvSkills
import kotlinx.android.synthetic.main.fragment_character_details.tvAttackValue
import kotlinx.android.synthetic.main.fragment_character_details.tvDefenseValue
import kotlinx.android.synthetic.main.fragment_character_details.tvName
import kotlinx.android.synthetic.main.fragment_character_details.tvSpecial
import kotlinx.android.synthetic.main.fragment_character_details.tvSpeedValue
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterDetailsFragment : BaseFragment<CharacterDetailsViewModel>() {

    override val layoutRes: Int = R.layout.fragment_character_details

    override val viewModel: CharacterDetailsViewModel by viewModel()

    override fun setupViews() {
        viewModel.character.observe(this) {
            onCharacter(it)
        }
    }

    private fun onCharacter(character: Character) {
//        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
//        Glide.with(imageView.context)
//            .load(character.imageUrl)
//            .fitCenter()
//            .placeholder(getCircularProgressDrawable(requireContext()))
//            .transition(DrawableTransitionOptions.withCrossFade(factory))
//            .into(imageView)

        setupStats(character)
        setupSkills(character.skills)
    }

    private fun setupStats(character: Character) {
        tvName.text = character.name
        tvAttackValue.text = character.attack.toString()
        tvDefenseValue.text = character.defense.toString()
        tvSpeedValue.text = character.speed.toString()
    }

    private fun setupSkills(skills: List<String>) {
        if (skills.isNotEmpty()) {
            tvSpecial.text = getString(R.string.abilities)
            rvSkills.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvSkills.adapter = SkillsAdapter(skills)
            rvSkills.visibility = View.VISIBLE
        } else {
            tvSpecial.text = getString(R.string.no_abilities)
            rvSkills.visibility = View.GONE
        }
    }
}
