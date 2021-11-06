package com.esaudev.wizeline.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.esaudev.wizeline.R
import com.esaudev.wizeline.databinding.FragmentDetailBinding
import com.esaudev.wizeline.databinding.FragmentListBinding
import com.esaudev.wizeline.extensions.mapToQuery
import com.esaudev.wizeline.extensions.toast
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.model.OrderBook
import com.esaudev.wizeline.ui.adapters.BindAdapter
import com.esaudev.wizeline.ui.adapters.BookAdapter
import com.esaudev.wizeline.ui.list.ListViewModel
import com.esaudev.wizeline.utils.Constants
import com.esaudev.wizeline.utils.Constants.BOOK_BUNDLE
import com.esaudev.wizeline.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private var listAdapter: BindAdapter? = null

    private lateinit var book: AvailableBook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            book = it.getParcelable(BOOK_BUNDLE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentDetailBinding
            .inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initObservers()
    }

    private fun init(){
        initComponents()
        viewModel.getAvailableBooks(book.book.mapToQuery())
    }

    private fun initComponents(){
        listAdapter = BindAdapter(requireContext())
        binding.rvBinds.adapter = listAdapter
    }

    private fun initObservers(){
        viewModel.getOrderBooks.observe(viewLifecycleOwner, { dataState ->
            when(dataState){
                is DataState.Loading -> showProgressBar()
                is DataState.Success -> handleSuccess(dataState.data)
                is DataState.Error -> handleError(dataState.error)
                else -> Unit
            }
        })
    }

    private fun handleSuccess(orderBook: OrderBook){
        hideProgressBar()
        listAdapter?.submitList(orderBook.bids)
    }

    private fun handleError(error: String){
        hideProgressBar()
        when(error){
            Constants.NETWORK_UNKNOWN_ERROR -> activity?.toast(getString(R.string.network__unknown_error))
            else -> activity?.toast(getString(R.string.network__unknown_error))
        }
    }

    private fun showProgressBar(){
        binding.gLoading.visibility = View.VISIBLE
        binding.gData.visibility = View.GONE
    }

    private fun hideProgressBar(){
        binding.gLoading.visibility = View.GONE
        binding.gData.visibility = View.VISIBLE
    }

}