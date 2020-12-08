package com.frankitoo.presentation.features.questdetails

import com.frankitoo.domain.models.lego.quest.Quest
import com.frankitoo.domain.repositories.QuestRepository
import com.frankitoo.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collect

class QuestDetailsViewModel(
    private val questRepository: QuestRepository,
) : BaseViewModel() {
    val quest by liveData<Quest> {
        val id = arguments.value?.let { QuestDetailsFragmentArgs.fromBundle(it) }?.questId
        id?.let {
            questRepository.fetchQuest(id).collect { emit(it) }
        }
    }
}
