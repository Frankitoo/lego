package com.frankito.presentation.utils

import android.content.Context
import android.util.DisplayMetrics

object WindowHelper {
    fun getItemWidthInColumns(context: Context, columns: Int, spacing: Int): Int {
        val screenWidth = getScreenWidth(context)
        return (screenWidth - ((columns + 1) * spacing)) / columns
    }

    private fun getScreenWidth(context: Context): Int {
        return getMetrics(context).widthPixels
    }

    fun pxFromDp(context: Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }

    private fun getMetrics(context: Context): DisplayMetrics {
        return context.resources.displayMetrics
    }
}