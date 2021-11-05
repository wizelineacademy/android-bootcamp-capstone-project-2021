package com.jbc7ag.cryptso.ui.currencieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jbc7ag.cryptso.R
import com.jbc7ag.cryptso.data.model.Book
import com.jbc7ag.cryptso.databinding.ItemCurrenciesBinding
import com.jbc7ag.cryptso.util.formatPrice
import com.jbc7ag.cryptso.util.getmarketFormat

typealias OnCurrencyClick = (String) -> Unit

class CurrenciesAdapter ( private val onCurrencyClick: OnCurrencyClick):
    ListAdapter<Book, CurrenciesAdapter.BookViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return LayoutInflater.from(parent.context)
            .let { inflater -> ItemCurrenciesBinding.inflate(inflater, parent, false) }
            .let { binding -> BookViewHolder(binding, onCurrencyClick) }
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookViewHolder(
        private val binding: ItemCurrenciesBinding,
        private val onCurrencyClick: OnCurrencyClick,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.apply {
                val currencyCode = book.name
                itemCurrenciesName.text = currencyCode.uppercase()
                itemCurrenciesData.text = book.maxPrice.formatPrice(book.book)
                itemCurrenciesValue.text = book.book.getmarketFormat()

                Glide.with(itemCurrenciesImage)
                    .load("https://cryptoicon-api.vercel.app/api/icon/${currencyCode}")
                    .fitCenter()
                    .placeholder(R.drawable.ic_baseline_monetization_on_24)
                    .apply(RequestOptions().override(80, 80))
                    .into(itemCurrenciesImage)

                root.setOnClickListener{
                    onCurrencyClick(book.book)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.book == newItem.book

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem == newItem
        }
    }
}