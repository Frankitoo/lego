package com.frankitoo.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.frankitoo.data.repositories.weapon.getPagingSource
import com.frankitoo.domain.models.lego.weapon.Weapon
import com.frankitoo.domain.repositories.WeaponRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class WeaponRepositoryImpl(private val db: FirebaseFirestore) : WeaponRepository {

    companion object {
        const val WEAPONS = "weapons"
    }

    override fun fetchWeapons(): Flow<PagingData<Weapon>> {
        return Pager(
            PagingConfig(pageSize = 20, enablePlaceholders = true, prefetchDistance = 6)
        ) {
            getPagingSource<Weapon>(FirebaseFirestore.getInstance(), WEAPONS)
        }.flow
    }

    @ExperimentalCoroutinesApi
    override suspend fun fetchWeapon(id: String): Flow<Weapon> = callbackFlow {
        val subscription = db.collection(WEAPONS).document(id).addSnapshotListener { snapshot, _ ->
            if (snapshot!!.exists()) {
                val weapon = snapshot.toObject(Weapon::class.java)
                weapon?.let {
                    offer(weapon)
                }
            }
        }
        awaitClose { subscription.remove() }
    }
}

