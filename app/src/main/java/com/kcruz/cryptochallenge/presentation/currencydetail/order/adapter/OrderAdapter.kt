package com.kcruz.cryptochallenge.presentation.currencydetail.order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kcruz.cryptochallenge.databinding.ItemOrderBinding
import com.kcruz.cryptochallenge.domain.Order

class OrderAdapter : ListAdapter<Order, OrderViewHolder> (DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder =
        OrderViewHolder(
            ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Order> () {
            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean =
                oldItem.oid == newItem.oid
        }
    }
}