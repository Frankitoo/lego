package com.frankitoo.presentation.features.vehiclelist

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.frankitoo.domain.models.lego.vehicle.Vehicle
import com.frankitoo.domain.repositories.VehicleRepository
import com.frankitoo.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class VehicleListViewModel(
    private val vehicleRepository: VehicleRepository
) : BaseViewModel() {

    fun fetchVehicles(): Flow<PagingData<Vehicle>> {
        return vehicleRepository.fetchVehicles().cachedIn(viewModelScope)
    }
}
