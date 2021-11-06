package com.example.cryptochallenge.ui.commons

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Base for ViewHolders for list
 *
 * @property T Define the type of item that show the viewHolder
 * @param view View of item
 */
abstract class BaseHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    /**
     * Property for get application resources
     */
    protected val resources: Resources = view.resources

    /**
     * Property for get the context of item
     */
    protected val context: Context = view.context

    /**
     * Set item information in the view
     *
     * @param item Information of item
     */
    abstract fun bind(item: T?)
}