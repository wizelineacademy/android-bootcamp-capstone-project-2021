package dev.ricsarabia.cryptochallenge.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ricsarabia.cryptochallenge.databinding.BookItemBinding
import dev.ricsarabia.cryptochallenge.domain.Book

class BooksAdapter(val onBookClick: (Book) -> Unit) : RecyclerView.Adapter<BooksViewHolder>() {
    var books = listOf<Book>()
        set(value) {
            field = value; notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(books[position], onBookClick)
    }

    override fun getItemCount() = books.size
}

class BooksViewHolder(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(book: Book, onBookClick: (Book) -> Unit) {
        binding.root.setOnClickListener { onBookClick(book) }
        binding.bookNameTextView.text = book.book
        Glide.with(binding.root).load(book.imageUrl).into(binding.bookImageView)
    }
}