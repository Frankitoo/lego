package com.frankitoo.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.frankitoo.domain.models.character.Character
import com.frankitoo.domain.repositories.CharacterRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl : CharacterRepository {

    companion object {
        const val CHARACTERS = "characters"
    }

    override fun fetchCharacters(): Flow<PagingData<Character>> {
        return Pager(
            PagingConfig(pageSize = 20, enablePlaceholders = false, prefetchDistance = 6)
        ) {
            CharacterPagingSource(FirebaseFirestore.getInstance())
        }.flow
    }

    override suspend fun fetchCharacter(name: String): Flow<Character> {
        TODO("Not yet implemented")
    }
}
