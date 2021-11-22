package com.jbc7ag.cryptso.ui.currencydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jbc7ag.cryptso.data.model.Bids
import com.jbc7ag.cryptso.databinding.ItemTradesBinding
import com.jbc7ag.cryptso.util.formatCurrency


class BidsAdapter : ListAdapter<Bids, BidsAdapter.BidsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BidsViewHolder {
        return LayoutInflater.from(parent.context)
            .let { inflater -> ItemTradesBinding.inflate(inflater, parent, false) }
            .let { binding -> BidsViewHolder(binding) }
    }

    override fun onBindViewHolder(holder: BidsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BidsViewHolder(
        private val binding: ItemTradesBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(bids: Bids) {
            binding.apply {
                itemTradesPrice.text = bids.price.formatCurrency()
                itemTradesAmount.text = bids.amount
                itemTradesTotal.text =
                    (bids.price.toFloat() * bids.amount.toFloat()).toString().formatCurrency()
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Bids>() {
            override fun areItemsTheSame(oldItem: Bids, newItem: Bids): Boolean =
                oldItem.book == newItem.book

            override fun areContentsTheSame(oldItem: Bids, newItem: Bids): Boolean =
                oldItem == newItem
        }
    }
}