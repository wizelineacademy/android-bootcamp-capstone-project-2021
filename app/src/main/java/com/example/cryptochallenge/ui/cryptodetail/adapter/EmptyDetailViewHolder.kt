package com.example.cryptochallenge.ui.cryptodetail.adapter

import com.example.cryptochallenge.databinding.ItemEmptyDetailBinding
import com.example.cryptochallenge.domain.DetailSectionItem
import com.example.cryptochallenge.ui.commons.BaseHolder

/**
 * View Holder for emtpy section
 *
 * @param binding Item' view
 */
class EmptyDetailViewHolder(binding: ItemEmptyDetailBinding) :
    BaseHolder<DetailSectionItem>(binding.root) {

    override fun bind(item: DetailSectionItem?) {

    }
}