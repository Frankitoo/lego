package com.frankitoo.domain.repositories

import androidx.paging.PagingData
import com.frankitoo.domain.models.character.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun fetchCharacters(): Flow<PagingData<Character>>
    suspend fun fetchCharacter(name: String): Flow<Character>
}
