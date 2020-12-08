package com.frankitoo.domain.repositories

import androidx.paging.PagingData
import com.frankitoo.domain.models.lego.quest.Quest
import kotlinx.coroutines.flow.Flow

interface QuestRepository {
    fun fetchQuests(): Flow<PagingData<Quest>>
    suspend fun fetchQuest(id: String): Flow<Quest>
}
