package com.frankito.presentation.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.frankito.presentation.R

fun View.startRotatedAnimation(context: Context) {
    val rotation = AnimationUtils.loadAnimation(context, R.anim.rotate_indefinitely)
    rotation.fillAfter = true
    this.startAnimation(rotation)
}

fun View.fadeOut() {
    this.animate()
        .alpha(0.0f)
        .setDuration(500L)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                visibility = View.GONE
            }
        })
}

