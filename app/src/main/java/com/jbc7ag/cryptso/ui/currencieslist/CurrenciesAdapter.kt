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
import com.jbc7ag.cryptso.util.*
import java.util.*

typealias OnCurrencyClick = (String) -> Unit

class CurrenciesAdapter(private val onCurrencyClick: OnCurrencyClick) :
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
                val imageSize =
                    binding.root.resources.getDimension(R.dimen.currency_list_image).toInt()
                val currencyCode = book.book.getCurrencyCode()
                val currencyFilter = book.book.getCurrencyCodeFilter()
                itemCurrenciesName.text = book.name?.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
                itemCurrenciesData.text = book.book.getmarketFormat()

                Glide.with(itemCurrenciesImage)
                    .load(IMAGES_URL + currencyCode)
                    .fitCenter()
                    .placeholder(R.drawable.ic_baseline_monetization_on_24)
                    .apply(RequestOptions().override(imageSize, imageSize))
                    .into(itemCurrenciesImage)

                root.setOnClickListener {
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