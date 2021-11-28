package com.example.bootcampproject.ui.availablebooks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bootcampproject.data.mock.AvailableBook
import com.example.bootcampproject.databinding.ItemAvailableBooksBinding
import com.example.bootcampproject.util.reformatNumber
import com.example.bootcampproject.util.returnURLImage

private const val ARROW_IMAGE_URL =
    "https://thypix.com/wp-content/uploads/2020/04/white-arrow-86.png"

typealias OnAvailableBooksClicked = (String) -> Unit

class AvailableBooksAdapter(
    private val onAvailableBooksClicked: OnAvailableBooksClicked,
) : ListAdapter<AvailableBook, AvailableBooksAdapter.AvailableBooksViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableBooksViewHolder {
        return LayoutInflater.from(parent.context)
            .let { inflater -> ItemAvailableBooksBinding.inflate(inflater, parent, false) }
            .let { binding -> AvailableBooksViewHolder(binding, onAvailableBooksClicked) }
    }

    override fun onBindViewHolder(holder: AvailableBooksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AvailableBooksViewHolder(
        private val binding: ItemAvailableBooksBinding,
        private val onAvailableBooksClicked: OnAvailableBooksClicked,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(payLoads: AvailableBook) {

            binding.textMinPrice.text = payLoads.minimum_price.reformatNumber()
            binding.textMaxPrice.text = payLoads.maximum_price.reformatNumber()
            Glide.with(binding.imageOriginalCurrency)
                .load(returnURLImage(payLoads.book.split("_")[0]))
                .into(binding.imageOriginalCurrency)
            Glide.with(binding.imageArrow)
                .load(ARROW_IMAGE_URL)
                .into(binding.imageArrow)
            Glide.with(binding.imageCurrencyExchange)
                .load(returnURLImage(payLoads.book.split("_")[1]))
                .into(binding.imageCurrencyExchange)
            binding.booktext.text = payLoads.book
            binding.cardViewContainer.setOnClickListener {
                onAvailableBooksClicked.invoke(payLoads.book)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AvailableBook>() {
            override fun areItemsTheSame(oldItem: AvailableBook, newItem: AvailableBook): Boolean =
                oldItem.book == newItem.book

            override fun areContentsTheSame(
                oldItem: AvailableBook,
                newItem: AvailableBook
            ): Boolean =
                oldItem == newItem
        }
    }
}
