package com.jbc7ag.cryptso.ui.currencydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jbc7ag.cryptso.R
import com.jbc7ag.cryptso.data.model.Bids
import com.jbc7ag.cryptso.data.model.Book
import com.jbc7ag.cryptso.data.model.BookDetail
import com.jbc7ag.cryptso.databinding.FragmentCurrencyDetailsBinding
import com.jbc7ag.cryptso.ui.currencieslist.CurrenciesAdapter
import com.jbc7ag.cryptso.ui.currencieslist.CurrenciesFragmentDirections
import com.jbc7ag.cryptso.ui.currencieslist.CurrenciesViewModel
import com.jbc7ag.cryptso.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.ticker

@AndroidEntryPoint
class CurrencyDetailFragment: Fragment() {

    private lateinit var binding: FragmentCurrencyDetailsBinding
    private val viewModel: CurrencyViewModel by viewModels()
    private lateinit var bidsAdapter: BidsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCurrencyDetailsBinding.inflate(layoutInflater, container, false)
            .apply { binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookName = requireArguments().getString("currency_id")
        viewModel.getTicker(bookName ?: "")
        viewModel.getOrders(bookName ?: "")
        observers()
    }

    private fun observers(){
        viewModel.apply {
            error.observe(viewLifecycleOwner, {
                Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
            })
            bookTicker.observe(viewLifecycleOwner,{
                fillDataTicker(it)
            })
            orders.observe(viewLifecycleOwner,{
                fillOrderList(it)
            })
        }
    }

    private fun fillDataTicker(data: BookDetail){
        binding.apply {
            val currencyCode = data.book.getCurrencyCode()
            detailCurrencyHigh.text = data.high.formatCurrency()
            detailCurrencyLow.text = data.low.formatCurrency()
            detailCurrencyMarket.text = data.book.getmarketFormat()
            detailCurrencyPrice.text = data.last.formatCurrency()

            Glide.with(detailCurrencyImage)
                .load("https://cryptoicon-api.vercel.app/api/icon/${currencyCode}")
                .fitCenter()
                .placeholder(R.drawable.ic_baseline_monetization_on_24)
                .apply(RequestOptions().override(200, 200))
                .into(detailCurrencyImage)
        }
    }

    private fun fillOrderList(data: List<Bids>){

        bidsAdapter = BidsAdapter()

        binding.tradesList.run {
            adapter = bidsAdapter
        }
        bidsAdapter.submitList(data)
    }
}