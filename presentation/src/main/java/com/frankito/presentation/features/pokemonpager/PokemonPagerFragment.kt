package com.frankito.presentation.features.pokemonpager

import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.frankito.presentation.R
import com.frankito.presentation.base.BaseFragment
import com.frankito.presentation.features.pokemondetails.PokemonDetailsFragment
import com.frankito.presentation.features.pokemonlist.PokemonListFragment
import kotlinx.android.synthetic.main.fragment_pokemon_pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.sharedViewModel

class PokemonPagerFragment : BaseFragment<PokemonPagerViewModel>() {

    companion object {
        const val NUM_OF_PAGES = 2
    }

    override val layoutRes: Int = R.layout.fragment_pokemon_pager

    override val viewModel: PokemonPagerViewModel by sharedViewModel()

    private lateinit var viewPager: ViewPager2

    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun setupViews() {
        viewPager = pokemonViewPager
        val pagerAdapter = PokemonPagerAdapter(requireActivity())
        viewPager.adapter = pagerAdapter

        viewPager.isUserInputEnabled = false

        viewModel.bindIntents()
        viewModel.pagerViewState
            .onEach { state ->
                delay(150L)
                handleState(state)
            }
            .launchIn(lifecycleScope)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setViewPagerUserInputEnabled(position)
                lifecycleScope.launchWhenStarted {
                    viewModel.processIntent(PokemonPagerIntent.PageChanged(position))
                }
            }
        })

        onBackPressedCallback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            onBackPressed()
        }
    }

    private fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            if (!findNavController().popBackStack()) {
                requireActivity().finish()
            }
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    private fun handleState(viewState: PokemonPagerViewState) {
        viewPager.setCurrentItem(viewState.currentItem, true)
        setViewPagerUserInputEnabled(viewState.currentItem)
    }

    private fun setViewPagerUserInputEnabled(position: Int) {
        viewPager.isUserInputEnabled = position == 1
    }

    private inner class PokemonPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_OF_PAGES

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> PokemonListFragment()
                1 -> PokemonDetailsFragment()
                else -> PokemonListFragment()
            }
        }
    }
}