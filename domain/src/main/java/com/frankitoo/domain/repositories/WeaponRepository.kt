package com.frankitoo.domain.repositories

import androidx.paging.PagingData
import com.frankitoo.domain.models.lego.weapon.Weapon
import kotlinx.coroutines.flow.Flow

interface WeaponRepository {
    fun fetchWeapons(): Flow<PagingData<Weapon>>
    suspend fun fetchWeapon(id: String): Flow<Weapon>
}

