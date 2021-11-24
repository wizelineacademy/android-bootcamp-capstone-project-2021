package dev.ricsarabia.cryptochallenge.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ricsarabia.cryptochallenge.databinding.BookItemBinding
import dev.ricsarabia.cryptochallenge.domain.Book

class BooksAdapter(private val onBookClick: (Book) -> Unit) :
    ListAdapter<Book, BooksViewHolder>(BookDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksViewHolder(binding)
    }
    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(getItem(position), onBookClick)
    }
}

class BooksViewHolder(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(book: Book, onBookClick: (Book) -> Unit) {
        binding.root.setOnClickListener { onBookClick(book) }
        binding.majorTextView.text = book.major.uppercase()
        binding.minorTextView.text = book.minor.uppercase()
        Glide.with(binding.root).load(book.imageUrl).into(binding.bookImageView)
    }
}

class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book) = oldItem.book == oldItem.book
    override fun areContentsTheSame(oldItem: Book, newItem: Book) = oldItem == newItem
}