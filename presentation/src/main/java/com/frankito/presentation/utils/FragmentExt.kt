package com.frankito.presentation.utils

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment

internal fun Fragment.hideKeyboard(view: View) {
    context?.getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(view.windowToken, 0)
}
