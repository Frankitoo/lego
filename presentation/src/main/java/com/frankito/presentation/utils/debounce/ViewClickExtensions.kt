package com.frankito.presentation.utils.debounce

import android.view.View

fun View.setOnClickListener(debounce: Boolean, block: (View) -> Unit) = if (debounce) {
    setOnClickListener(DebouncingOnClickListener(block))
} else {
    setOnClickListener { block(it) }
}
