package com.kennedydias.dogspictures.extensions

import android.view.View

fun View.showWithAlphaAnimation(
    customStartDelay: Long = 0,
    customDuration: Long = 300,
    callback: (() -> Unit)? = null
) {
    this.visibility = View.VISIBLE
    animate().apply {
        startDelay = customStartDelay
        alpha(1f)
        duration = customDuration
        withEndAction {
            callback?.invoke()
        }
        start()
    }
}

fun View.hideWithAlphaAnimation(
    customStartDelay: Long = 0,
    customDuration: Long = 300,
    callback: (() -> Unit)? = null
) {
    val view: View? = this
    animate().apply {
        startDelay = customStartDelay
        alpha(0f)
        duration = customDuration
        withEndAction {
            view?.visibility = View.GONE
            callback?.invoke()
        }
        start()
    }
}