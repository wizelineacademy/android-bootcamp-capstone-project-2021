package dev.ricsarabia.cryptochallenge.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ricsarabia.cryptochallenge.databinding.MainFragmentBinding
import dev.ricsarabia.cryptochallenge.domain.Book
import dev.ricsarabia.cryptochallenge.ui.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding // TODO: correct this horrible screen design
    private lateinit var viewModel: MainViewModel
    private val booksAdapter = BooksAdapter{ onBookClick(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Visual components
        binding.booksLinearLayout.layoutManager = LinearLayoutManager(context)
        binding.booksLinearLayout.adapter = booksAdapter

        //Setting viewModel
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        // Retrieving books data
        viewModel.getBooks()

        // Observers
        viewModel.books.observe(viewLifecycleOwner, {
            booksAdapter.books = it
        })
        viewModel.loading.observe(viewLifecycleOwner, {
            Log.wtf("loading", it.toString())
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Log.wtf("errorMessage", it)
        })
    }

    private fun onBookClick(book: Book) {
        viewModel.selectedBook.value = book.book
        Log.wtf("onBookClick", book.toString())
    }

}