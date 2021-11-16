package com.example.bootcampproject.ui.orderbooks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcampproject.data.mock.Bids
import com.example.bootcampproject.databinding.ItemBidsBinding

class BidsAdapter :
    ListAdapter<Bids, BidsAdapter.BidsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BidsViewHolder {
        return LayoutInflater.from(parent.context)
            .let { inflater -> ItemBidsBinding.inflate(inflater, parent, false) }
            .let { binding -> BidsViewHolder(binding) }
    }

    override fun onBindViewHolder(holder: BidsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class BidsViewHolder(
        private val binding: ItemBidsBinding,
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(bid: Bids){
            binding.bidsText.text=bid.price.toString()
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