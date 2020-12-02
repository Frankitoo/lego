package com.frankito.domain.repositories

import androidx.paging.PagingData
import com.frankito.domain.models.character.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun fetchCharacters(): Flow<PagingData<Character>>
    suspend fun fetchCharacter(name: String): Character
}
