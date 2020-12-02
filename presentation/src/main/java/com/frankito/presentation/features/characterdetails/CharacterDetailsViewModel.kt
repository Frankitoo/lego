package com.frankito.presentation.features.characterdetails

import com.frankito.domain.models.character.Character
import com.frankito.domain.repositories.CharacterRepository
import com.frankito.presentation.base.BaseViewModel

class CharacterDetailsViewModel(
    private val characterRepository: CharacterRepository,
) : BaseViewModel() {

    val character by liveData<Character> {
        val id = arguments.value?.let { CharacterDetailFragmentArgs.fromBundle(it) }
        characterRepository.fetchCharacter().collect { emit(it) }
    }
}
