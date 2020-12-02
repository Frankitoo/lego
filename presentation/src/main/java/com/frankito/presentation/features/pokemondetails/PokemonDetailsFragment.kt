package com.frankito.presentation.features.pokemondetails

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.frankito.domain.models.pokemon.PokemonDetail
import com.frankito.domain.models.pokemon.PokemonListItem
import com.frankito.presentation.R
import com.frankito.presentation.base.BaseFragment
import com.frankito.presentation.features.pokemonpager.PokemonPagerViewModel
import com.frankito.presentation.utils.fadeOut
import com.frankito.presentation.utils.getCircularProgressDrawable
import com.frankito.presentation.utils.getTypeColor
import com.frankito.presentation.utils.startRotatedAnimation
import kotlinx.android.synthetic.main.fragment_pokemon_details.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class PokemonDetailsFragment : BaseFragment<PokemonDetailsViewModel>() {

    override val layoutRes: Int = R.layout.fragment_pokemon_details

    override val viewModel: PokemonDetailsViewModel by viewModel()

    private val sharedPagerViewModel: PokemonPagerViewModel by sharedViewModel()

    override fun setupViews() {
        viewModel.bindIntents()
        viewModel.pokemonDetailViewState
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)

        sharedPagerViewModel.pagerViewState
            .onEach { state -> handlePagerState(state.selectedPokemon) }
            .launchIn(lifecycleScope)

        tvPrimaryType.visibility = View.GONE
        tvSecondaryType.visibility = View.GONE
    }

    private fun handlePagerState(selectedPokemon: PokemonListItem?) {
        selectedPokemon?.let {
            lifecycleScope.launchWhenStarted {
                viewModel.processIntent(PokemonDetailIntents.FetchPokemon(selectedPokemon.name))
            }
        }
    }

    private fun handleState(viewState: PokemonDetailViewState) {
        onLoading(viewState.isLoading)
        viewState.pokemonDetail?.let {
            onPokemonDetailChanged(it)
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

    private fun onPokemonDetailChanged(pokemonDetail: PokemonDetail) {
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        Glide.with(imageView.context)
            .load(pokemonDetail.imageUrl)
            .fitCenter()
            .placeholder(getCircularProgressDrawable(requireContext()))
            .transition(DrawableTransitionOptions.withCrossFade(factory))
            .into(imageView)

        setupStats(pokemonDetail)
        setupTypeViews(pokemonDetail.types)
        setupAbilities(pokemonDetail.abilities)
    }

    private fun setupStats(pokemonDetail: PokemonDetail) {
        tvName.text = pokemonDetail.name

        val heightString =
            "${String.format("%.1f", pokemonDetail.height.value)} ${pokemonDetail.height.unit}"
        tvHeightValue.text = heightString

        val weightString = "${pokemonDetail.weight.value} ${pokemonDetail.weight.unit}"
        tvWeightValue.text = weightString

        val experienceString =
            "${pokemonDetail.experience.value} ${pokemonDetail.experience.unit}"
        tvExperienceValue.text = experienceString
    }

    private fun setupTypeViews(types: List<String>) {
        if (types.isNotEmpty()) {
            val type = types.first()
            tvPrimaryType.text = type
            val color = ContextCompat.getColor(requireContext(), type.getTypeColor())
            tvPrimaryType.backgroundTintList = ColorStateList.valueOf(color)
            tvPrimaryType.visibility = View.VISIBLE
        } else {
            tvPrimaryType.visibility = View.GONE
        }
        if (types.size >= 2) {
            val type = types[1]
            tvSecondaryType.text = type
            val color = ContextCompat.getColor(requireContext(), type.getTypeColor())
            tvSecondaryType.backgroundTintList = ColorStateList.valueOf(color)
            tvSecondaryType.visibility = View.VISIBLE
        } else {
            tvSecondaryType.visibility = View.GONE
        }
    }

    private fun setupAbilities(abilities: List<String>) {
        if (abilities.isNotEmpty()) {
            tvAbilities.text = getString(R.string.abilities)
            rvAbilities.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvAbilities.adapter = AbilityAdapter(abilities)
            rvAbilities.visibility = View.VISIBLE
        } else {
            tvAbilities.text = getString(R.string.no_abilities)
            rvAbilities.visibility = View.GONE
        }
    }
}