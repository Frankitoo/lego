package com.frankitoo.presentation.features.weapondetails

import com.frankitoo.domain.models.lego.weapon.Weapon
import com.frankitoo.domain.repositories.WeaponRepository
import com.frankitoo.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collect

class WeaponDetailsViewModel(
    private val weaponRepository: WeaponRepository,
) : BaseViewModel() {
    val weapon by liveData<Weapon> {
        val id = arguments.value?.let { WeaponDetailsFragmentArgs.fromBundle(it) }?.weaponId
        id?.let {
            weaponRepository.fetchWeapon(id).collect { emit(it) }
        }
    }
}
