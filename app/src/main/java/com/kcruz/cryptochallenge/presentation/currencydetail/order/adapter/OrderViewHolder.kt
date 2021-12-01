package com.kcruz.cryptochallenge.presentation.currencydetail.order.adapter

import androidx.recyclerview.widget.RecyclerView
import com.kcruz.cryptochallenge.R
import com.kcruz.cryptochallenge.databinding.ItemOrderBinding
import com.kcruz.cryptochallenge.domain.Order

class OrderViewHolder(private val binding: ItemOrderBinding): RecyclerView.ViewHolder (binding.root) {

    fun bind(order: Order) {
        binding.tvBook.text = binding.root.context.getString(R.string.book, order.book)
        binding.tvPrice.text = binding.root.context.getString(R.string.price, order.price)
        binding.tvAmount.text = binding.root.context.getString(R.string.amount, order.amount)
    }
}