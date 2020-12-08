package com.frankitoo.data.repositories.weapon

import androidx.paging.PagingSource
import com.frankitoo.domain.models.lego.Item
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

inline fun <reified T : Item> getPagingSource(
    db: FirebaseFirestore,
    collection: String
): PagingSource<QuerySnapshot, T> {
    return object : PagingSource<QuerySnapshot, T>() {
        override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, T> {
            return try {

                val currentPage = params.key ?: db.collection(collection)
                    .limit(20)
                    .get()
                    .await()

                val lastDocumentSnapshot = currentPage.documents[currentPage.size() - 1]

                val nextPage = db.collection(collection)
                    .limit(20)
                    .startAfter(lastDocumentSnapshot)
                    .get()
                    .await()

                val itemList = ArrayList<T>()

                for (document in currentPage.documents) {
                    val item = document.toObject(T::class.java)
                    item?.id = document.id
                    item?.let {
                        itemList.add(item)
                    }
                }

                LoadResult.Page(
                    data = itemList,
                    prevKey = null,
                    nextKey = nextPage
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}
