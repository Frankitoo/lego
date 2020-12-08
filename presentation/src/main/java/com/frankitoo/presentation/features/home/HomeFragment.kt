package com.frankitoo.presentation.features.home

import androidx.navigation.fragment.findNavController
import com.frankitoo.presentation.R
import com.frankitoo.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.charactersCard
import kotlinx.android.synthetic.main.fragment_home.questsCard
import kotlinx.android.synthetic.main.fragment_home.vehiclesCard
import kotlinx.android.synthetic.main.fragment_home.weaponsCard
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel>() {

    override val layoutRes: Int = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModel()

    override fun setupViews() {
        charactersCard.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toCharacterList())
        }
        weaponsCard.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toWeaponList())
        }
        vehiclesCard.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toVehicleList())
        }
        questsCard.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toQuestList())
        }
    }
}
