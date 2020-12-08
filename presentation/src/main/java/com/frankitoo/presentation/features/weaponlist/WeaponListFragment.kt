package com.frankitoo.presentation.features.weaponlist

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.frankitoo.presentation.R
import com.frankitoo.presentation.base.BaseFragment
import com.frankitoo.presentation.utils.LoadingAdapter
import com.frankitoo.presentation.utils.getGridDecoration
import kotlinx.android.synthetic.main.fragment_list.recyclerView
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.viewmodel.ext.android.viewModel

class WeaponListFragment : BaseFragment<WeaponListViewModel>() {

    companion object {
        const val SPAN_COUNT = 3
    }

    override val layoutRes: Int = R.layout.fragment_list

    override val viewModel: WeaponListViewModel by viewModel()

    private val weaponListAdapter = WeaponListAdapter()

    override fun setupViews() {
        initAdapter()
    }

    private fun initAdapter() {
        weaponListAdapter.onClickListener =
            { item ->
                lifecycleScope.launchWhenCreated {
                    item.id?.let {
                        findNavController().navigate(WeaponListFragmentDirections.toWeaponDetails(it))
                    }
                }
            }

        recyclerView.addItemDecoration(getGridDecoration(requireContext()))
        recyclerView.layoutManager = GridLayoutManager(activity, SPAN_COUNT)
        recyclerView.adapter = weaponListAdapter.withLoadStateHeaderAndFooter(
            header = LoadingAdapter { weaponListAdapter.retry() },
            footer = LoadingAdapter { weaponListAdapter.retry() }
        )

        lifecycleScope.launchWhenStarted {
            viewModel.fetchWeapons().collectLatest { pagingData ->
                weaponListAdapter.submitData(pagingData)
            }
        }
    }
}

