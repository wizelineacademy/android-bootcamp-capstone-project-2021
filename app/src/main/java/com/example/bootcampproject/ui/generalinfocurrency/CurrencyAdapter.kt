package com.example.bootcampproject.ui.generalinfocurrency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bootcampproject.databinding.ItemCurrencyBinding
import com.example.bootcampproject.domain.Currency

typealias OnCurrencyClicked = (Long) -> Unit

class CurrencyAdapter(
    private val onCurrencyClicked: OnCurrencyClicked,
):ListAdapter<Currency,CurrencyAdapter.CurrencyViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):CurrencyViewHolder{
        return LayoutInflater.from(parent.context)
            .let { inflater -> ItemCurrencyBinding.inflate(inflater, parent, false) }
            .let { binding -> CurrencyViewHolder(binding, onCurrencyClicked) }
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder,position: Int){
        holder.bind(getItem(position))
    }

    class CurrencyViewHolder(
        private val binding:ItemCurrencyBinding,
        private val onCurrencyClicked: OnCurrencyClicked,
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(currency: Currency){
            binding.nameCurrency.text=currency.name
            Glide.with(binding.imageCurrency)
                .load(currency.imageUrl)
                .into(binding.imageCurrency)
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Currency>() {
            override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
                oldItem.code == newItem.code

            override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean =
                oldItem == newItem
        }
    }
}
