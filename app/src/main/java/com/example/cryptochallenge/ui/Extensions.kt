package com.example.cryptochallenge.ui

import android.widget.ImageView
import com.bumptech.glide.Glide

class Extensions {
    companion object {
        const val MAJOR = "major"
        const val MINOR = "minor"
        const val DIVISOR = "_"
        const val NUM_CURRENCY_PER_BOOK = 2

        fun ImageView.loadCurrencyImg(imgId: Int) {
            Glide.with(this).load(imgId).circleCrop().into(this)
        }

        fun String.getMajorAndMinor(): Map<String, String>? {
            val value = split(DIVISOR)
            return when {
                value.isNullOrEmpty() -> null
                value.size == NUM_CURRENCY_PER_BOOK -> mapOf(
                    MAJOR to value.first(),
                    MINOR to value.last()
                )
                else -> null
            }
        }
    }
}