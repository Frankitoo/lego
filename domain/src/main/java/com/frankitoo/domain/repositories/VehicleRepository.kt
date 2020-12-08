package com.frankitoo.domain.repositories

import androidx.paging.PagingData
import com.frankitoo.domain.models.lego.vehicle.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    fun fetchVehicles(): Flow<PagingData<Vehicle>>
    suspend fun fetchVehicle(id: String): Flow<Vehicle>
}
