package com.example.cryptochallenge.ui.cryptodetail.adapter

import com.example.cryptochallenge.databinding.ItemEmptyDetailBinding
import com.example.cryptochallenge.domain.DetailSectionItem
import com.example.cryptochallenge.ui.commons.BaseHolder

class EmptyDetailViewHolder(private val binding: ItemEmptyDetailBinding) :
    BaseHolder<DetailSectionItem>(binding.root) {
    override fun bind(item: DetailSectionItem?) {

    }
}