package com.example.cryptochallenge.ui.cryptodetail.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptochallenge.databinding.ItemOrderBookDetailBinding
import com.example.cryptochallenge.domain.DetailSectionItem
import com.example.cryptochallenge.domain.orderbook.PayloadObject
import com.example.cryptochallenge.ui.commons.BaseHolder

/**
 * ViewHolder for show order books in cryptocurrency details
 *
 * @property binding Item' view
 */
class OrderBookDetailViewHolder(private val binding: ItemOrderBookDetailBinding) :
    BaseHolder<DetailSectionItem>(binding.root) {

    override fun bind(item: DetailSectionItem?) {
        if (item !is DetailSectionItem && item?.content !is List<*>)
            return

        val orderBooks = item.content as List<PayloadObject>

        val orderBooksAdapter = OrderBooksAdapter()
        orderBooksAdapter.submitList(orderBooks)
        binding.rvOrderBookList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = orderBooksAdapter
        }
    }

    /**
     * Set title section
     *
     * @param sectionName Section name
     */
    fun setTitleSection(sectionName: String) {
        binding.tvOrderBookLabel.text = sectionName
    }
}