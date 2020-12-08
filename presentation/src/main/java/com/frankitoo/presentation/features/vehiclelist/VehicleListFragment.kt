package com.frankitoo.presentation.features.vehiclelist

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.frankitoo.domain.models.lego.vehicle.Vehicle
import com.frankitoo.presentation.R
import com.frankitoo.presentation.base.BaseFragment
import com.frankitoo.presentation.utils.LoadingAdapter
import com.frankitoo.presentation.utils.PagingGridListAdapter
import com.frankitoo.presentation.utils.getGridDecoration
import kotlinx.android.synthetic.main.fragment_list.recyclerView
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.viewmodel.ext.android.viewModel

class VehicleListFragment : BaseFragment<VehicleListViewModel>() {

    companion object {
        const val SPAN_COUNT = 3
    }

    override val layoutRes: Int = R.layout.fragment_list

    override val viewModel: VehicleListViewModel by viewModel()

    private val vehicleListAdapter = PagingGridListAdapter<Vehicle>(SPAN_COUNT)

    override fun setupViews() {
        initAdapter()
    }

    private fun initAdapter() {
        vehicleListAdapter.onClickListener =
            { item ->
                lifecycleScope.launchWhenCreated {
                    item.id?.let {
                        findNavController().navigate(VehicleListFragmentDirections.toVehicleDetails(it))
                    }
                }
            }

        recyclerView.addItemDecoration(getGridDecoration(SPAN_COUNT, requireContext()))
        recyclerView.layoutManager = GridLayoutManager(activity, SPAN_COUNT)
        recyclerView.adapter = vehicleListAdapter.withLoadStateHeaderAndFooter(
            header = LoadingAdapter { vehicleListAdapter.retry() },
            footer = LoadingAdapter { vehicleListAdapter.retry() }
        )

        lifecycleScope.launchWhenStarted {
            viewModel.fetchVehicles().collectLatest { pagingData ->
                vehicleListAdapter.submitData(pagingData)
            }
        }
    }
}


