package dev.ricsarabia.cryptochallenge.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ricsarabia.cryptochallenge.R
import dev.ricsarabia.cryptochallenge.databinding.MainFragmentBinding
import dev.ricsarabia.cryptochallenge.domain.Book
import dev.ricsarabia.cryptochallenge.ui.MainViewModel

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding // TODO: correct this horrible screen design
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }
    private val booksAdapter = BooksAdapter{ onBookClick(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Init components
        binding.booksLinearLayout.layoutManager = LinearLayoutManager(context)
        binding.booksLinearLayout.adapter = booksAdapter

        // Init observers
        viewModel.books.observe(viewLifecycleOwner, { booksAdapter.books = it })
        viewModel.loading.observe(viewLifecycleOwner, { Log.wtf("loading", it.toString()) })
        viewModel.errorMessage.observe(viewLifecycleOwner, { Log.wtf("errorMessage", it) })

        // Retrieving books data
        viewModel.getBooks()
        // TODO: Show a "loading" animation while retrieving data
    }

    private fun onBookClick(book: Book) {
        viewModel.selectedBook.value = book.book
        findNavController().navigate(R.id.detailFragment_to_detailFragment)
    }
}