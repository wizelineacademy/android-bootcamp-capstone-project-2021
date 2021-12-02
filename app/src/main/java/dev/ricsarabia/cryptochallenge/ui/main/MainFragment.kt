package dev.ricsarabia.cryptochallenge.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.ricsarabia.cryptochallenge.R
import dev.ricsarabia.cryptochallenge.databinding.MainFragmentBinding
import dev.ricsarabia.cryptochallenge.domain.Book

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private val booksAdapter = BooksAdapter { onBookClick(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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

    private fun initViews() = binding.run {
        booksLinearLayout.layoutManager = GridLayoutManager(context, 2)
        booksLinearLayout.adapter = booksAdapter
        booksSwipeRefresh.setOnRefreshListener { viewModel.updateBooks() }
    }

    private fun initObservers() = viewModel.run {
        books.observe(viewLifecycleOwner) { booksAdapter.submitList(it) }
        gettingBooks.observe(viewLifecycleOwner) { binding.booksSwipeRefresh.isRefreshing = it }
    }

    private fun onBookClick(book: Book) {
        findNavController().navigate(
            R.id.detailFragment_to_detailFragment,
            bundleOf("BOOK" to book.book)
        )
    }
}
