package com.example.cryptochallenge.ui.cryptodetail.adapter

import com.example.cryptochallenge.databinding.ItemOrderBookPayloadBinding
import com.example.cryptochallenge.domain.orderbook.PayloadObject
import com.example.cryptochallenge.ui.commons.BaseHolder

/**
 * ViewHolder for order book items
 *
 * @property binding Item' view
 */
class OrderBookViewHolder(private val binding: ItemOrderBookPayloadBinding) :
    BaseHolder<PayloadObject>(binding.root) {

    override fun bind(item: PayloadObject?) {
        if (item !is PayloadObject)
            return

        binding.tvNumOrder.text = (adapterPosition + 1).toString()
        binding.tvOrderPrice.text = item.price.toString()
        binding.tvOrderAmount.text = item.amount.toString()
    }
}