package com.example.bootcampproject.ui.orderbooks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcampproject.data.mock.Asks
import com.example.bootcampproject.databinding.ItemAsksBinding
import com.example.bootcampproject.util.reformatNumber

class AsksAdapter() :
    ListAdapter<Asks, AsksAdapter.AsksViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsksViewHolder {
        return LayoutInflater.from(parent.context)
            .let { inflater -> ItemAsksBinding.inflate(inflater, parent, false) }
            .let { binding -> AsksViewHolder(binding) }
    }

    override fun onBindViewHolder(holder: AsksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AsksViewHolder(
        private val binding: ItemAsksBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ask: Asks) {
            binding.askPriceText.text = ask.price.reformatNumber()
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Asks>() {
            override fun areItemsTheSame(oldItem: Asks, newItem: Asks): Boolean =
                oldItem.book == newItem.book

            override fun areContentsTheSame(oldItem: Asks, newItem: Asks): Boolean =
                oldItem == newItem
        }
    }
}
