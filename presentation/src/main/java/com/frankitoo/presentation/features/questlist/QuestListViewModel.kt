package com.frankitoo.presentation.features.questlist

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.frankitoo.domain.models.lego.quest.Quest
import com.frankitoo.domain.repositories.QuestRepository
import com.frankitoo.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class QuestListViewModel(
    private val questRepository: QuestRepository
) : BaseViewModel() {

    fun fetchQuests(): Flow<PagingData<Quest>> {
        return questRepository.fetchQuests().cachedIn(viewModelScope)
    }
}

