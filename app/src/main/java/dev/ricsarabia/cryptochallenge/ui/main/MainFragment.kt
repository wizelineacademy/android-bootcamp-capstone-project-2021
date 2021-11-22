package dev.ricsarabia.cryptochallenge.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dev.ricsarabia.cryptochallenge.R
import dev.ricsarabia.cryptochallenge.databinding.MainFragmentBinding
import dev.ricsarabia.cryptochallenge.domain.Book
import dev.ricsarabia.cryptochallenge.ui.detail.DetailViewModel

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(DetailViewModel::class.java) }
    private val booksAdapter = BooksAdapter { onBookClick(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initObservers()
        viewModel.updateBooks()
    }

    private fun initViews() {
        binding.booksLinearLayout.layoutManager = GridLayoutManager(context, 2)
        binding.booksLinearLayout.adapter = booksAdapter
    }

    private fun initObservers() {
        viewModel.books.observe(viewLifecycleOwner, { booksAdapter.books = it })
        viewModel.loading.observe(viewLifecycleOwner, { binding.mainProgressBar.isVisible = it })
        viewModel.errorMessage.observe(viewLifecycleOwner, { Log.wtf("errorMessage", it) })
    }

    private fun onBookClick(book: Book) {
        viewModel.selectedBook.value = book.book
        findNavController().navigate(R.id.detailFragment_to_detailFragment)
    }
}