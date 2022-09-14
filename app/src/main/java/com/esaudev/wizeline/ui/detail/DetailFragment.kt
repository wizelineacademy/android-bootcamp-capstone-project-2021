package com.esaudev.wizeline.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.esaudev.wizeline.databinding.FragmentDetailBinding
import com.esaudev.wizeline.extensions.hide
import com.esaudev.wizeline.extensions.mapToQuery
import com.esaudev.wizeline.extensions.show
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.model.OrderBook
import com.esaudev.wizeline.model.Ticker
import com.esaudev.wizeline.ui.adapters.AskAdapter
import com.esaudev.wizeline.ui.adapters.BidAdapter
import com.esaudev.wizeline.utils.Constants.BOOK_BUNDLE
import com.esaudev.wizeline.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private var bidAdapter: BidAdapter? = null
    private var askAdapter: AskAdapter? = null

    private lateinit var book: AvailableBook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            book = it.getParcelable(BOOK_BUNDLE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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

    private fun init() {
        initComponents()
        //viewModel.getTickerFromBook(book.book.mapToQuery())
        //viewModel.getOrderBooks(book.book.mapToQuery())
        //viewModel.getViewData(book = book.book.mapToQuery())
    }

    private fun initMinMax(ticker: Ticker) {
        with(binding) {
            tvHeader.text = book.book
            tvMaximum.text = ticker.high
            tvMinimum.text = ticker.low
            tvLastPrice.text = ticker.last
        }
    }

    private fun initComponents() {
        bidAdapter = BidAdapter(requireContext())
        askAdapter = AskAdapter(requireContext())

        binding.rvBinds.adapter = bidAdapter
        binding.rvAsks.adapter = askAdapter
    }

    private fun initObservers() {

        viewModel.getTickerState.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Loading -> showProgressBar()
                is DataState.Success -> handleTickerSuccess(dataState.data)
                is DataState.Error -> handleError(dataState.error)
                else -> Unit
            }
        }

        viewModel.getOrderBookState.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Loading -> showProgressBar()
                is DataState.Success -> handleOrderBookSuccess(dataState.data)
                is DataState.Error -> handleError(dataState.error)
                else -> Unit
            }
        }
    }

    private fun handleTickerSuccess(ticker: Ticker) {
        hideEmptyState()
        initMinMax(ticker)
    }

    private fun handleOrderBookSuccess(orderBook: OrderBook) {
        hideProgressBar()
        hideEmptyState()
        bidAdapter?.submitList(orderBook.bids)
        askAdapter?.submitList(orderBook.asks)
    }

    private fun handleError(error: String) {
        hideProgressBar()
        showEmptyState()
    }

    private fun showEmptyState() {
        with(binding) {
            nsvContainer.hide()
            nsvEmptyState.show()
        }
    }

    private fun hideEmptyState() {
        with(binding) {
            nsvEmptyState.hide()
            nsvContainer.show()
        }
    }

    private fun showProgressBar() {
        binding.gLoading.show()
        binding.gData.hide()
    }

    private fun hideProgressBar() {
        binding.gLoading.hide()
        binding.gData.show()
    }
}
