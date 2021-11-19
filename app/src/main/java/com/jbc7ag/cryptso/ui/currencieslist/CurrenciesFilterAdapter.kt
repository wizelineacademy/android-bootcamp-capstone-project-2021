package com.jbc7ag.cryptso.ui.currencieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jbc7ag.cryptso.R
import com.jbc7ag.cryptso.data.model.Filter
import com.jbc7ag.cryptso.databinding.ItemCurrenciesMarketBinding
import com.jbc7ag.cryptso.util.IMAGES_URL

typealias OnFilterClick = (String) -> Unit

class CurrenciesFilterAdapter(private val onFilterClick: OnFilterClick) :
    ListAdapter<Filter, CurrenciesFilterAdapter.BookFilterViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookFilterViewHolder {
        return LayoutInflater.from(parent.context)
            .let { inflater -> ItemCurrenciesMarketBinding.inflate(inflater, parent, false) }
            .let { binding -> BookFilterViewHolder(binding, onFilterClick) }
    }

    override fun onBindViewHolder(holder: BookFilterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookFilterViewHolder(
        private val binding: ItemCurrenciesMarketBinding,
        private val onFilterClick: OnFilterClick,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(filter: Filter) {
            binding.apply {
                val imageSize =
                    binding.root.resources.getDimension(R.dimen.currency_list_image).toInt()
                val currencyCode = filter.name
                itemCurrenciesMarketName.text = currencyCode.uppercase()
                itemCurrenciesMarketCheck.visibility = if (filter.selected) View.VISIBLE else View.GONE

                Glide.with(itemCurrenciesMarketImage)
                    .load(IMAGES_URL + currencyCode)
                    .fitCenter()
                    .placeholder(R.drawable.ic_baseline_coin_24)
                    .apply(RequestOptions().override(imageSize, imageSize))
                    .into(itemCurrenciesMarketImage)

                root.setOnClickListener {
                    onFilterClick(filter.name)

                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Filter>() {
            override fun areItemsTheSame(oldItem: Filter, newItem: Filter): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Filter, newItem: Filter): Boolean =
                oldItem.name == newItem.name
        }
    }
}