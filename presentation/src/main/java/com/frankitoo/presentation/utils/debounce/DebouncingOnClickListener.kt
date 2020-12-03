package com.frankitoo.presentation.utils.debounce

import android.view.View
import com.frankitoo.presentation.R

class DebouncingOnClickListener(listener: (View) -> Unit) : View.OnClickListener {
    private val delegate = DebounceDelegate(R.id.view_debounced_click_last_timestamp, listener)

    override fun onClick(v: View) = delegate.execute(v)
}
