package com.frankitoo.data.repositories

import androidx.paging.PagingSource
import com.frankitoo.data.mapping.toDomain
import com.frankitoo.data.models.CharacterDto
import com.frankitoo.data.repositories.CharacterRepositoryImpl.Companion.CHARACTERS
import com.frankitoo.domain.models.character.Character
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class CharacterPagingSource(private val db: FirebaseFirestore) : PagingSource<QuerySnapshot, Character>() {

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, Character> {
        return try {
            val storageRef = FirebaseStorage.getInstance().reference

            val currentPage = params.key ?: db.collection(CHARACTERS)
                .limit(20)
                .get()
                .await()

            val lastDocumentSnapshot = currentPage.documents[currentPage.size() - 1]

            val nextPage = db.collection(CHARACTERS)
                .limit(20)
                .startAfter(lastDocumentSnapshot)
                .get()
                .await()

            LoadResult.Page(
                data = currentPage.toObjects(CharacterDto::class.java).map { it.toDomain(storageRef) },
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
