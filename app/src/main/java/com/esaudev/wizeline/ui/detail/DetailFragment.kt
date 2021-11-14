package com.esaudev.wizeline.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.esaudev.wizeline.R
import com.esaudev.wizeline.databinding.FragmentDetailBinding
import com.esaudev.wizeline.extensions.hide
import com.esaudev.wizeline.extensions.mapToQuery
import com.esaudev.wizeline.extensions.show
import com.esaudev.wizeline.extensions.toast
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.model.OrderBook
import com.esaudev.wizeline.model.Ticker
import com.esaudev.wizeline.ui.adapters.AskAdapter
import com.esaudev.wizeline.ui.adapters.BidAdapter
import com.esaudev.wizeline.utils.Constants
import com.esaudev.wizeline.utils.Constants.BOOK_BUNDLE
import com.esaudev.wizeline.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_book.*

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
        viewModel.getTickerFromBook(book.book.mapToQuery())
    }

    private fun initMinMax(ticker: Ticker){
        with(binding){
            tvHeader.text = book.book
            tvMaximum.text = ticker.high
            tvMinimum.text = ticker.low
            tvLastPrice.text = ticker.last
        }
    }

    private fun initComponents(){
        bidAdapter = BidAdapter(requireContext())
        askAdapter = AskAdapter(requireContext())

        binding.rvBinds.adapter = bidAdapter
        binding.rvAsks.adapter = askAdapter
    }

    private fun initObservers(){

        viewModel.getTicker.observe(viewLifecycleOwner,{ dataState ->
            when(dataState){
                is DataState.Loading -> showProgressBar()
                is DataState.Success -> handleTickerSuccess(dataState.data)
                is DataState.Error -> handleError(dataState.error)
                else -> Unit
            }
        })

        viewModel.getOrderBooks.observe(viewLifecycleOwner, { dataState ->
            when(dataState){
                is DataState.Success -> handleOrderBookSuccess(dataState.data)
                is DataState.Error -> handleError(dataState.error)
                else -> Unit
            }
        })
    }

    private fun handleTickerSuccess(ticker: Ticker){
        initMinMax(ticker)
        viewModel.getOrderBooks(book.book.mapToQuery())
    }

    private fun handleOrderBookSuccess(orderBook: OrderBook){
        hideProgressBar()
        bidAdapter?.submitList(orderBook.bids)
        askAdapter?.submitList(orderBook.asks)
    }

    private fun handleError(error: String){
        hideProgressBar()
        when(error){
            Constants.NETWORK_UNKNOWN_ERROR -> activity?.toast(getString(R.string.network__unknown_error))
            else -> activity?.toast(getString(R.string.network__unknown_error))
        }
    }

    private fun showProgressBar(){
        binding.gLoading.show()
        binding.gData.hide()
    }

    private fun hideProgressBar(){
        binding.gLoading.hide()
        binding.gData.show()
    }

}