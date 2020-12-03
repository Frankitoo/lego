package com.frankitoo.presentation.features.characterlist

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.frankitoo.domain.models.character.Character
import com.frankitoo.domain.repositories.CharacterRepository
import com.frankitoo.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class CharacterListViewModel(
    private val characterRepository: CharacterRepository
) : BaseViewModel() {
    
    fun fetchCharacters(): Flow<PagingData<Character>> {
        return characterRepository.fetchCharacters().cachedIn(viewModelScope)
    }
}
