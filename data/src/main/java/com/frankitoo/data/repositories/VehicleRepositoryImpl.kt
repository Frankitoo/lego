package com.frankitoo.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.frankitoo.data.repositories.weapon.getPagingSource
import com.frankitoo.domain.models.lego.vehicle.Vehicle
import com.frankitoo.domain.repositories.VehicleRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class VehicleRepositoryImpl(private val db: FirebaseFirestore) : VehicleRepository {

    companion object {
        const val VEHICLES = "vehicles"
    }

    override fun fetchVehicles(): Flow<PagingData<Vehicle>> {
        return Pager(
            PagingConfig(pageSize = 20, enablePlaceholders = true, prefetchDistance = 6)
        ) {
            getPagingSource<Vehicle>(FirebaseFirestore.getInstance(), VEHICLES)
        }.flow
    }

    @ExperimentalCoroutinesApi
    override suspend fun fetchVehicle(id: String): Flow<Vehicle> = callbackFlow {
        val subscription = db.collection(VEHICLES).document(id).addSnapshotListener { snapshot, _ ->
            if (snapshot!!.exists()) {
                val vehicle = snapshot.toObject(Vehicle::class.java)
                vehicle?.let {
                    offer(vehicle)
                }
            }
        }
        awaitClose { subscription.remove() }
    }
}

