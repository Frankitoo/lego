package com.frankitoo.presentation.features.characterdetails

import com.frankitoo.domain.models.character.Character
import com.frankitoo.domain.repositories.CharacterRepository
import com.frankitoo.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collect

class CharacterDetailsViewModel(
    private val characterRepository: CharacterRepository,
) : BaseViewModel() {
    val character by liveData<Character> {
        val id = arguments.value?.let { CharacterDetailsFragmentArgs.fromBundle(it) }?.characterId
        id?.let {
            characterRepository.fetchCharacter(id).collect { emit(it) }
        }
    }
}
