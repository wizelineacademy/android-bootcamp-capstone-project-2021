package com.example.cryptochallenge.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptochallenge.databinding.ItemCryptocurrencyBinding
import com.example.cryptochallenge.domain.availablebook.Payload

class CryptocurrencyAdapter : ListAdapter<Payload, CryptocurrencyViewHolder>(DIFF_CALLBACK) {
    var onItemSelect: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrencyViewHolder {
        return returnCryptoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CryptocurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun returnCryptoViewHolder(parent: ViewGroup): CryptocurrencyViewHolder {
        val holder = CryptocurrencyViewHolder(
            ItemCryptocurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        holder.setOnClickListener {
            onItemSelect?.invoke(getItem(holder.adapterPosition).book ?: "")
        }
        return holder
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Payload>() {
            override fun areItemsTheSame(oldItem: Payload, newItem: Payload): Boolean {
                return oldItem.book == newItem.book &&
                        oldItem.minimum_amount == newItem.minimum_amount &&
                        oldItem.maximum_amount == newItem.maximum_amount &&
                        oldItem.minimum_price == newItem.minimum_price &&
                        oldItem.maximum_price == newItem.maximum_price &&
                        oldItem.minimum_value == newItem.minimum_value &&
                        oldItem.maximum_value == newItem.maximum_value
            }

            override fun areContentsTheSame(oldItem: Payload, newItem: Payload): Boolean {
                return oldItem == newItem
            }
        }
    }
}