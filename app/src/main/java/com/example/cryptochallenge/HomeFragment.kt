package com.example.cryptochallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cryptochallenge.common.makeToast
import com.example.cryptochallenge.data.model.Book
import com.example.cryptochallenge.data.model.GetBookFailure
import com.example.cryptochallenge.data.model.GetBookSuccess
import com.example.cryptochallenge.data.model.GetBooks
import com.example.cryptochallenge.databinding.FragmentHomeBinding
import com.example.cryptochallenge.ui.adapter.BookAdapter
import com.example.cryptochallenge.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), BookAdapter.OnBookClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private var bookAdapter: BookAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        initObservers()
        getBooks()
    }

    private fun initComponents() {
        bookAdapter = BookAdapter(requireContext(), this)
        binding.recyclerView.adapter = bookAdapter
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.layoutManager = gridLayoutManager
    }

    private fun initObservers() {
        viewModel.getBookEvent.observe(viewLifecycleOwner, { result ->
            when (result) {
                is GetBookSuccess -> {
                    bookAdapter?.submitList(result.list)
                }
                is GetBookFailure -> {
                    makeToast(":( ${result.exception.localizedMessage}")
                }
            }
        })
    }

    private fun getBooks() {
        viewModel.getBooksWithRx()
        //viewModel.getBooks()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onBookClickListener(book: Book) {
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundleOf("ID" to book.name))
    }
}