package com.frankito.presentation.utils.debounce

import android.view.View
import androidx.annotation.IdRes

class DebounceDelegate(
    @IdRes private val tagId: Int,
    private val block: (View) -> Unit
) {
    fun execute(v: View) {
        val lastClick = v.getTag(tagId) as? Long
        val current = System.currentTimeMillis()

        if (lastClick == null || current - lastClick >= DEBOUNCE_INTERVAL) {
            v.setTag(tagId, current)
            block(v)
        }
    }

    companion object {
        private const val DEBOUNCE_INTERVAL = 500L
    }
}
