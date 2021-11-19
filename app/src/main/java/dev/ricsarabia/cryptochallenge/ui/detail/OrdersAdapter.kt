package dev.ricsarabia.cryptochallenge.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.ricsarabia.cryptochallenge.databinding.OrderItemBinding
import dev.ricsarabia.cryptochallenge.domain.BookOrder
import dev.ricsarabia.cryptochallenge.utils.asDecimalAmount
import dev.ricsarabia.cryptochallenge.utils.asDecimalPrice

class OrdersAdapter : RecyclerView.Adapter<OrdersViewHolder>() {
    var orders = listOf<BookOrder>()
        set(value) {
            field = value; notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount() = orders.size
}

class OrdersViewHolder(val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(order: BookOrder) {
        binding.priceTextView.text = order.price.asDecimalPrice()
        binding.amountTextView.text = order.amount.asDecimalAmount()
    }
}