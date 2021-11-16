package com.example.bootcampproject.ui.orderbooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcampproject.data.mock.OrderBook
import com.example.bootcampproject.data.mock.Ticker
import com.example.bootcampproject.databinding.FragmentResumeInfoBinding

import com.example.bootcampproject.util.reformatNumber
import dagger.hilt.android.AndroidEntryPoint

private const val VIEW_HOLDER_SCREEN_PROPORTION = 1.0 / 5.0
@AndroidEntryPoint
class OrderBooksFragment() :Fragment(){

    private var _binding: FragmentResumeInfoBinding? = null
    private val binding: FragmentResumeInfoBinding
        get() = _binding!!

    private val viewModel: OrderBooksViewModel by viewModels()

    private lateinit var asksAdapter: AsksAdapter
    private lateinit var bidsAdapter: BidsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentResumeInfoBinding
            .inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val code = requireArguments().getString("code")
        asksAdapter= AsksAdapter()
        bidsAdapter= BidsAdapter()
        viewModel.getActualTicker(code)
        viewModel.getActualCurrencies(code)
        viewModel.orderbooks.observe(viewLifecycleOwner,{ orderBooks->
            fillInfoOrderBooks(orderBooks)
        })
        viewModel.tickers.observe(viewLifecycleOwner,{ tickers->
            fillTickers(tickers)
        })
    }
    private fun fillInfoOrderBooks(orderBooks:OrderBook){
        binding.date.text=orderBooks.updated_at
        binding.askList.run {
            adapter=asksAdapter
            layoutManager = object : LinearLayoutManager(requireContext()) {
                override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                    lp?.height = (height * VIEW_HOLDER_SCREEN_PROPORTION).toInt()
                    return true
                }
            }
        }
        binding.bidList.run {
            adapter=bidsAdapter
            layoutManager = object : LinearLayoutManager(requireContext()) {
                override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                    lp?.height = (height * VIEW_HOLDER_SCREEN_PROPORTION).toInt()
                    return true
                }
            }
        }
        bidsAdapter.submitList(orderBooks.bids)
        asksAdapter.submitList(orderBooks.asks)
    }

    private fun fillTickers(tickers: Ticker){
        binding.idMinValText.text=tickers.low.reformatNumber()
        binding.idMaxValText.text=tickers.high.reformatNumber()
    }
}