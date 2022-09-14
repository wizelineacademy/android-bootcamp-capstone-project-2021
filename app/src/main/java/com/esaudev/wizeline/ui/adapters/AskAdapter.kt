package com.esaudev.wizeline.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.esaudev.wizeline.databinding.ItemBindBinding
import com.esaudev.wizeline.model.Ask

class AskAdapter(
    private val context: Context
) : ListAdapter<Ask, BaseViewHolder<*>>(DiffUtilCallback) {

    private object DiffUtilCallback : DiffUtil.ItemCallback<Ask>() {
        override fun areItemsTheSame(oldItem: Ask, newItem: Ask): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Ask, newItem: Ask): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemBindBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolder -> holder.bind(getItem(position), position)
            else -> {}
        }
    }

    inner class BindViewHolder(private val binding: ItemBindBinding) : BaseViewHolder<Ask>(binding.root) {
        override fun bind(item: Ask, position: Int) = with(binding) {

            tvHeader.text = item.book
            tvPrice.text = item.price
            tvAmount.text = item.amount
        }
    }
}
