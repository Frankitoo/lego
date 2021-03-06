package com.frankitoo.presentation.utils

import android.content.Context
import android.graphics.PorterDuff
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.frankitoo.presentation.R

internal fun getCircularProgressDrawable(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 12f
    circularProgressDrawable.centerRadius = 80f
    circularProgressDrawable.setColorFilter(
        ContextCompat.getColor(
            context,
            R.color.light_yellow
        ), PorterDuff.Mode.SRC_ATOP
    )
    circularProgressDrawable.start()
    return circularProgressDrawable
}
