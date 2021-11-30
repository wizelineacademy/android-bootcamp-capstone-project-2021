package com.esaudev.wizeline.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.esaudev.wizeline.R

fun ImageView.load(url: String) {
    if (url.isNotEmpty()) {
        Glide.with(this.context).load(url).error(R.drawable.ic_coin).into(this)
    }
}
