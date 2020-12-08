package com.frankitoo.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.frankitoo.data.repositories.weapon.getPagingSource
import com.frankitoo.domain.models.lego.quest.Quest
import com.frankitoo.domain.repositories.QuestRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class QuestRepositoryImpl(private val db: FirebaseFirestore) : QuestRepository {

    companion object {
        const val QUESTS = "quests"
    }

    override fun fetchQuests(): Flow<PagingData<Quest>> {
        return Pager(
            PagingConfig(pageSize = 20, enablePlaceholders = true, prefetchDistance = 6)
        ) {
            getPagingSource<Quest>(FirebaseFirestore.getInstance(), QUESTS)
        }.flow
    }

    @ExperimentalCoroutinesApi
    override suspend fun fetchQuest(id: String): Flow<Quest> = callbackFlow {
        val subscription = db.collection(QUESTS).document(id).addSnapshotListener { snapshot, _ ->
            if (snapshot!!.exists()) {
                val quest = snapshot.toObject(Quest::class.java)
                quest?.let {
                    offer(quest)
                }
            }
        }
        awaitClose { subscription.remove() }
    }
}


