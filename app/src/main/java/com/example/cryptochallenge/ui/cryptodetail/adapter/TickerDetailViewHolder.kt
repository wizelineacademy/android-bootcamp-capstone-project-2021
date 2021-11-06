package com.example.cryptochallenge.ui.cryptodetail.adapter

import com.example.cryptochallenge.databinding.ItemTickerDetailBinding
import com.example.cryptochallenge.domain.DetailSectionItem
import com.example.cryptochallenge.domain.ticker.Payload
import com.example.cryptochallenge.ui.commons.BaseHolder

/**
 * ViewHolder for show Ticker in Detail Section
 *
 * @property binding Item' view
 */
class TickerDetailViewHolder(private val binding: ItemTickerDetailBinding) :
    BaseHolder<DetailSectionItem>(binding.root) {

    override fun bind(item: DetailSectionItem?) {
        if (item !is DetailSectionItem || item.content !is Payload)
            return

        val payload = item.content

        binding.tvTickerHighPrice.text = payload.high.toString()
        binding.tvTickerLastPrice.text = payload.last.toString()
        binding.tvTickerLowPrice.text = payload.low.toString()
    }
}