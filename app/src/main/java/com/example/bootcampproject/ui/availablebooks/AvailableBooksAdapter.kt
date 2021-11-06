package com.example.bootcampproject.ui.availablebooks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcampproject.data.mock.Payload
import com.example.bootcampproject.databinding.ItemAvailableBooksBinding


typealias OnAvailableBooksClicked = (Long) -> Unit

class AvailableBooksAdapter(
    private val onAvailableBooksClicked: OnAvailableBooksClicked,
): ListAdapter<Payload, AvailableBooksAdapter.AvailableBooksViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):AvailableBooksViewHolder{
        return LayoutInflater.from(parent.context)
            .let { inflater -> ItemAvailableBooksBinding.inflate(inflater, parent, false) }
            .let { binding -> AvailableBooksViewHolder(binding, onAvailableBooksClicked) }
    }

    override fun onBindViewHolder(holder: AvailableBooksViewHolder,position: Int){
        holder.bind(getItem(position))
    }

    class AvailableBooksViewHolder(
        private val binding: ItemAvailableBooksBinding,
        private val onAvailableBooksClicked: OnAvailableBooksClicked,
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(payLoads: Payload){
            binding.textMinPrice.text=payLoads.minimum_price.toString()
            binding.textMaxPrice.text=payLoads.maximum_price.toString()
            binding.textMinAmount.text=payLoads.minimum_amount.toString()
            binding.textMaxAmount.text=payLoads.maximum_amount.toString()
            binding.textMinValue.text=payLoads.minimum_value.toString()
            binding.textMaxValue.text=payLoads.maximum_value.toString()
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Payload>() {
            override fun areItemsTheSame(oldItem: Payload, newItem: Payload): Boolean =
                oldItem.book == newItem.book

            override fun areContentsTheSame(oldItem: Payload, newItem: Payload): Boolean =
                oldItem == newItem
        }
    }
}