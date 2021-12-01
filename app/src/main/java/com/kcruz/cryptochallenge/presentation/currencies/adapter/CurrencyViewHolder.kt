package com.kcruz.cryptochallenge.presentation.currencies.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kcruz.cryptochallenge.databinding.ItemCurrencyBinding
import com.kcruz.cryptochallenge.domain.ExchangeOrderBook

class CurrencyViewHolder(private val binding: ItemCurrencyBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ExchangeOrderBook) {
        binding.tvCurrency.text = item.book
    }

    fun setSelectionListener(listener: View.OnClickListener) {
        binding.clCurrencyContainer.setOnClickListener(listener)
    }

}