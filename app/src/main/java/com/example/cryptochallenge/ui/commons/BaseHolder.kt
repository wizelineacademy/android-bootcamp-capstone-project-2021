package com.example.cryptochallenge.ui.commons

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    protected val resources: Resources = view.resources
    protected val context: Context = view.context

    abstract fun bind(item: T?)
}