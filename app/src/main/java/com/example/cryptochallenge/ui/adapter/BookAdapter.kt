package com.example.cryptochallenge.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.cryptochallenge.core.BaseViewHolder
import com.example.cryptochallenge.data.model.Book
import com.example.cryptochallenge.databinding.BookItemRowBinding


class BookAdapter(
    private val context: Context,
    private val itemClickListener: OnBookClickListener
): ListAdapter<Book, BaseViewHolder<*>>(DiffUtilCallback) {
    interface OnBookClickListener {
        fun onBookClickListener(book: Book)
    }

    private object DiffUtilCallback: DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = BookItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is BookViewHolder -> holder.bind(getItem(position), position)
            else -> {}
        }
    }
//
    inner class BookViewHolder(private val binding: BookItemRowBinding): BaseViewHolder<Book>(binding.root) {
        override fun bind(item: Book, position: Int) = with(binding){
            Glide.with(context)
                .load(item.image)
                .circleCrop()
                .into(bookItemImage)

            val cryptoName = item.name.split("_")
            textViewItemName.text = "${cryptoName[0].uppercase()} en ${cryptoName[1].uppercase()}"
            bookItemCard.setOnClickListener{ itemClickListener.onBookClickListener(item)}
        }
    }
}
