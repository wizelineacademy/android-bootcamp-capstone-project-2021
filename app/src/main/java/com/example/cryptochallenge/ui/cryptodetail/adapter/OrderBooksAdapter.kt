package com.example.cryptochallenge.ui.cryptodetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptochallenge.databinding.ItemOrderBookPayloadBinding
import com.example.cryptochallenge.domain.orderbook.PayloadObject
import com.example.cryptochallenge.ui.commons.BaseHolder

/**
 * Adapter for order books elements
 */
class OrderBooksAdapter : ListAdapter<PayloadObject, BaseHolder<PayloadObject>>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<PayloadObject> {
        return OrderBookViewHolder(
            ItemOrderBookPayloadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseHolder<PayloadObject>, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        /**
         * Property for calculate differences between items
         */
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PayloadObject>() {
            override fun areItemsTheSame(oldItem: PayloadObject, newItem: PayloadObject): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PayloadObject,
                newItem: PayloadObject
            ): Boolean {
                return oldItem.amount == newItem.amount
                        && oldItem.book == newItem.book
                        && oldItem.price == newItem.price
            }

        }
    }
}