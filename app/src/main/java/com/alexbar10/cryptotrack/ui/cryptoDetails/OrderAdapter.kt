package com.alexbar10.cryptotrack.ui.cryptoDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexbar10.cryptotrack.databinding.OrderItemBinding
import com.alexbar10.cryptotrack.domain.OrderDetailItem
import com.alexbar10.cryptotrack.utils.currencyFormat

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.CurrencyOrderViewHolder>() {

    private val cryptocurrencyOrderItems: MutableList<OrderDetailItem> = mutableListOf()

    fun setData(cryptocurrencyOrders: List<OrderDetailItem>) {
        cryptocurrencyOrderItems.clear()
        cryptocurrencyOrderItems.addAll(cryptocurrencyOrders)
        notifyDataSetChanged()
    }

    override fun getItemCount() = cryptocurrencyOrderItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyOrderViewHolder {
        return LayoutInflater.from(parent.context)
            .let { layoutInflater -> OrderItemBinding.inflate(layoutInflater, parent, false) }
            .let { binding -> CurrencyOrderViewHolder(binding) }
    }

    override fun onBindViewHolder(holder: CurrencyOrderViewHolder, position: Int) {
        holder.bind(cryptocurrencyOrderItems[position])
    }

    class CurrencyOrderViewHolder(
        private val binding: OrderItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currencyOrderItem: OrderDetailItem) {
            binding.cryptocurrencyOrderPrice.text = currencyOrderItem.currencyFormat()
            binding.cryptocurrencyOrderAmount.text = currencyOrderItem.amount.toString()
            binding.cryptocurrencyOrderTotal.text = currencyOrderItem.currencyFormat(currencyOrderItem.total)
        }
    }
}
