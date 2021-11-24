package dev.ricsarabia.cryptochallenge.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.ricsarabia.cryptochallenge.databinding.OrderItemBinding
import dev.ricsarabia.cryptochallenge.domain.BookOrder
import dev.ricsarabia.cryptochallenge.utils.asDecimalAmount
import dev.ricsarabia.cryptochallenge.utils.asDecimalPrice

class OrdersAdapter : ListAdapter<BookOrder, OrdersViewHolder>(BookOrderDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersViewHolder(binding)
    }
    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class OrdersViewHolder(private val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(order: BookOrder) {
        binding.priceTextView.text = order.price.asDecimalPrice()
        binding.amountTextView.text = order.amount.asDecimalAmount()
    }
}

class BookOrderDiffCallback : DiffUtil.ItemCallback<BookOrder>() {
    override fun areItemsTheSame(oldItem: BookOrder, newItem: BookOrder) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: BookOrder, newItem: BookOrder) = oldItem == newItem
}
