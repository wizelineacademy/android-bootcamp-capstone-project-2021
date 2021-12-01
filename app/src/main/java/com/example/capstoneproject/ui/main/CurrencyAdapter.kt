package com.example.capstoneproject.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstoneproject.databinding.ItemCurrencyBinding
import com.example.capstoneproject.model.api.Currency

typealias OnCurrencyClicked = (String) -> Unit

class CurrencyAdapter(
    private val onCurrencyClicked: OnCurrencyClicked,
) : ListAdapter<Currency, CurrencyAdapter.CurrencyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return LayoutInflater.from(parent.context)
            .let { inflater -> ItemCurrencyBinding.inflate(inflater, parent, false) }
            .let { binding -> CurrencyViewHolder(binding, onCurrencyClicked) }
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CurrencyViewHolder(
        private val binding: ItemCurrencyBinding,
        private val onCurrencyClicked: OnCurrencyClicked,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
            binding.currencyName.text = currency.book
            val imageUrl: String = "https://cryptoicon-api.vercel.app/api/icon/" +
                    currency.book.substringBefore("_", "")
            Glide.with(binding.currencyImage.context).load(imageUrl).fitCenter()
                .into(binding.currencyImage)

            binding.currencyBackground.setOnClickListener { onCurrencyClicked(currency.book) }
            binding.currencyName.setOnClickListener { onCurrencyClicked(currency.book) }
            binding.currencyImage.setOnClickListener { onCurrencyClicked(currency.book) }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Currency>() {
            override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
                oldItem.book == newItem.book

            override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean =
                oldItem == newItem
        }
    }
}