package com.example.cryptochallenge.ui.cryptodetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptochallenge.databinding.ItemEmptyDetailBinding
import com.example.cryptochallenge.databinding.ItemHeaderDetailBinding
import com.example.cryptochallenge.databinding.ItemOrderBookDetailBinding
import com.example.cryptochallenge.databinding.ItemTickerDetailBinding
import com.example.cryptochallenge.domain.DetailSectionItem
import com.example.cryptochallenge.domain.SectionType
import com.example.cryptochallenge.ui.commons.BaseHolder

/**
 * Adapter for Detail' Sections
 */
class SectionsAdapter :
    ListAdapter<DetailSectionItem, BaseHolder<DetailSectionItem>>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<DetailSectionItem> {
        return when (viewType) {
            SectionType.HEADER.ordinal -> HeaderDetailViewHolder(
                ItemHeaderDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            SectionType.TICKER.ordinal -> TickerDetailViewHolder(
                ItemTickerDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            SectionType.ASK.ordinal, SectionType.BID.ordinal -> createOrderBookViewHolder(
                viewType,
                parent
            )
            else -> EmptyDetailViewHolder(
                ItemEmptyDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: BaseHolder<DetailSectionItem>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.ordinal
    }

    /**
     * Create an [OrderBookViewHolder] and set section title
     *
     * @param type Type' section
     * @param parent The ViewGroup into which the new View will be added after it is bound to an
     * adapter position
     */
    private fun createOrderBookViewHolder(
        type: Int,
        parent: ViewGroup
    ): BaseHolder<DetailSectionItem> {
        val holder = OrderBookDetailViewHolder(
            ItemOrderBookDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        holder.setTitleSection(
            when (type) {
                SectionType.BID.ordinal -> SectionType.BID.name
                SectionType.ASK.ordinal -> SectionType.ASK.name
                else -> ""
            }
        )
        return holder
    }

    companion object {
        /**
         * Property for calculate differences between items
         */
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailSectionItem>() {
            override fun areItemsTheSame(
                oldItem: DetailSectionItem,
                newItem: DetailSectionItem
            ): Boolean {
                return oldItem.type == newItem.type &&
                        oldItem.content == newItem.content
            }

            override fun areContentsTheSame(
                oldItem: DetailSectionItem,
                newItem: DetailSectionItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}