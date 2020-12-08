package com.frankitoo.presentation.features.weaponlist

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.frankitoo.domain.models.lego.weapon.Weapon
import com.frankitoo.domain.repositories.WeaponRepository
import com.frankitoo.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class WeaponListViewModel(
    private val weaponRepository: WeaponRepository
) : BaseViewModel() {

    fun fetchWeapons(): Flow<PagingData<Weapon>> {
        return weaponRepository.fetchWeapons().cachedIn(viewModelScope)
    }
}
