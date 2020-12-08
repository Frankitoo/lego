package com.frankitoo.presentation.features.vehicledetails

import com.frankitoo.domain.models.lego.vehicle.Vehicle
import com.frankitoo.domain.repositories.VehicleRepository
import com.frankitoo.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collect

class VehicleDetailsViewModel(
    private val vehicleRepository: VehicleRepository,
) : BaseViewModel() {
    val vehicle by liveData<Vehicle> {
        val id = arguments.value?.let { VehicleDetailsFragmentArgs.fromBundle(it) }?.vehicleId
        id?.let {
            vehicleRepository.fetchVehicle(id).collect { emit(it) }
        }
    }
}
