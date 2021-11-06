package com.esaudev.wizeline.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.esaudev.wizeline.R
import com.esaudev.wizeline.databinding.ItemBookBinding
import com.esaudev.wizeline.extensions.load
import com.esaudev.wizeline.model.AvailableBook

class BookAdapter(
    private val context: Context,
    private val itemClickListener: OnBookClickListener
): ListAdapter<AvailableBook, BaseViewHolder<*>>(DiffUtilCallback) {

    interface OnBookClickListener{
        fun onBookClickListener(book: AvailableBook)
    }

    private object DiffUtilCallback: DiffUtil.ItemCallback<AvailableBook>() {
        override fun areItemsTheSame(oldItem: AvailableBook, newItem: AvailableBook): Boolean = oldItem.book == newItem.book
        override fun areContentsTheSame(oldItem: AvailableBook, newItem: AvailableBook): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is BookViewHolder -> holder.bind(getItem(position), position)
            else -> {}
        }
    }

    inner class BookViewHolder(private val binding: ItemBookBinding): BaseViewHolder<AvailableBook>(binding.root) {
        override fun bind(item: AvailableBook, position: Int) = with(binding) {
            ivBook.load(item.icon)

            tvBook.text = item.book

            tvPriceMin.text = context.getString(R.string.list__minimum_placeholder, item.minimum_price)
            tvPriceMax.text = context.getString(R.string.list__maximum_placeholder, item.maximum_price)

            tvAmountMin.text = context.getString(R.string.list__minimum_placeholder, item.minimum_amount)
            tvAmountMax.text = context.getString(R.string.list__maximum_placeholder, item.maximum_amount)

            tvValueMin.text = context.getString(R.string.list__minimum_placeholder, item.minimum_value)
            tvValueMax.text = context.getString(R.string.list__maximum_placeholder, item.maximum_value)

            mcvBook.setOnClickListener { itemClickListener.onBookClickListener(item) }
        }
    }
}