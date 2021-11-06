package com.esaudev.wizeline.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.esaudev.wizeline.R
import com.esaudev.wizeline.data.remote.responses.BidResponse
import com.esaudev.wizeline.databinding.ItemBindBinding
import com.esaudev.wizeline.databinding.ItemBookBinding
import com.esaudev.wizeline.extensions.load
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.model.Bid

class BidAdapter(
    private val context: Context
): ListAdapter<Bid, BaseViewHolder<*>>(DiffUtilCallback) {

    private object DiffUtilCallback: DiffUtil.ItemCallback<Bid>() {
        override fun areItemsTheSame(oldItem: Bid, newItem: Bid): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Bid, newItem: Bid): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemBindBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is BindViewHolder -> holder.bind(getItem(position), position)
            else -> {}
        }
    }

    inner class BindViewHolder(private val binding: ItemBindBinding): BaseViewHolder<Bid>(binding.root) {
        override fun bind(item: Bid, position: Int) = with(binding) {

            tvHeader.text = item.book
            tvPrice.text = item.price
            tvAmount.text = item.amount
        }
    }
}