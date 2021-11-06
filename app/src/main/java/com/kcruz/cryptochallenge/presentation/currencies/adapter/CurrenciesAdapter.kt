package com.kcruz.cryptochallenge.presentation.currencies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kcruz.cryptochallenge.databinding.ItemCurrencyBinding
import com.kcruz.cryptochallenge.domain.ExchangeOrderBook

class CurrenciesAdapter() :
    ListAdapter<ExchangeOrderBook, CurrencyViewHolder>(DIFF_CALLBACK) {

    var onItemSelected: ((ExchangeOrderBook) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val holder = CurrencyViewHolder(
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        holder.setSelectionListener {
            onItemSelected?.invoke(currentList[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ExchangeOrderBook>() {
            override fun areContentsTheSame(
                oldItem: ExchangeOrderBook,
                newItem: ExchangeOrderBook
            ): Boolean =
                newItem == oldItem


            override fun areItemsTheSame(
                oldItem: ExchangeOrderBook,
                newItem: ExchangeOrderBook
            ): Boolean {
                return oldItem.book == newItem.book
            }
        }
    }
}