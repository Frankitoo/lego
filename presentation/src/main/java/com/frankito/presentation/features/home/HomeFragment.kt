package com.frankito.presentation.features.home

import com.frankito.presentation.R
import com.frankito.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel>() {

    override val layoutRes: Int = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModel()

    override fun setupViews() {

    }
}
