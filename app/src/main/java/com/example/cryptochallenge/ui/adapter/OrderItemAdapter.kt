package com.example.cryptochallenge.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptochallenge.R
import com.example.cryptochallenge.common.ORDER_TYPE_ASK
import com.example.cryptochallenge.common.ORDER_TYPE_BID
import com.example.cryptochallenge.common.toNumberFormat
import com.example.cryptochallenge.core.BaseViewHolder
import com.example.cryptochallenge.data.model.Ask
import com.example.cryptochallenge.data.model.Bid
import com.example.cryptochallenge.databinding.OrderItemRowBinding
import java.util.*


class OrderItemAdapter(
    private val context: Context
): ListAdapter<OrderItem, BaseViewHolder<*>>(DiffUtilCallback) {
    private object DiffUtilCallback: DiffUtil.ItemCallback<OrderItem>() {
        override fun areItemsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = OrderItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is OrderViewHolder -> holder.bind(getItem(position), position)
            else -> {}
        }
    }
//
    inner class OrderViewHolder(private val binding: OrderItemRowBinding): BaseViewHolder<OrderItem>(binding.root) {
        override fun bind(item: OrderItem, position: Int) {
            if (item.orderType == ORDER_TYPE_ASK) {
                binding.textViewOrderLeft.text = item.amount.toNumberFormat()
                binding.textViewOrderRight.text = item.price.toNumberFormat()
                binding.textViewOrderRight.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.green
                    )
                )
            } else if (item.orderType == ORDER_TYPE_BID) {
                binding.textViewOrderLeft.text = item.price.toNumberFormat()
                binding.textViewOrderRight.text = item.amount.toNumberFormat()
                binding.textViewOrderLeft.setTextColor(ContextCompat.getColor(context, R.color.red))
            }
        }
    }
}

data class OrderItem(val id: String = UUID.randomUUID().toString().take(10), val orderType: String, val price: String, val amount: String)
fun Ask.toOrderItem() = OrderItem(orderType = ORDER_TYPE_ASK, price = this.price, amount = this.amount)
fun Bid.toOrderItem() = OrderItem(orderType = ORDER_TYPE_BID, price = this.price, amount = this.amount)
