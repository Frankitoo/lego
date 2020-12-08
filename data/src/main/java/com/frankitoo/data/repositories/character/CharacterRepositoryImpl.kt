package com.frankitoo.data.repositories.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.frankitoo.data.repositories.weapon.getPagingSource
import com.frankitoo.domain.models.lego.character.Character
import com.frankitoo.domain.repositories.CharacterRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CharacterRepositoryImpl(private val db: FirebaseFirestore) : CharacterRepository {

    companion object {
        const val CHARACTERS = "characters"
    }

    override fun fetchCharacters(): Flow<PagingData<Character>> {
        return Pager(
            PagingConfig(pageSize = 20, enablePlaceholders = true, prefetchDistance = 6)
        ) {
            getPagingSource<Character>(FirebaseFirestore.getInstance(), CHARACTERS)
        }.flow
    }

    @ExperimentalCoroutinesApi
    override suspend fun fetchCharacter(id: String): Flow<Character> = callbackFlow {
        val subscription = db.collection(CHARACTERS).document(id).addSnapshotListener { snapshot, _ ->
            if (snapshot!!.exists()) {
                val character = snapshot.toObject(Character::class.java)
                character?.let {
                    offer(character)
                }
            }
        }
        awaitClose { subscription.remove() }
    }
}
