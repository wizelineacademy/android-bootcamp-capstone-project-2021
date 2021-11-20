package com.kcruz.cryptochallenge.presentation.currencydetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.kcruz.cryptochallenge.R
import com.kcruz.cryptochallenge.databinding.FragmentCurrencyDetailBinding
import com.kcruz.cryptochallenge.domain.Book
import com.kcruz.cryptochallenge.domain.Order
import com.kcruz.cryptochallenge.presentation.currencydetail.adapter.OrderPageAdapter
import com.kcruz.cryptochallenge.presentation.currencydetail.order.OrderType

//TODO: Add documentation

class CurrencyDetailFragment : Fragment() {

    private val args: CurrencyDetailFragmentArgs by navArgs()
    private var binding: FragmentCurrencyDetailBinding? = null
    private val viewModel by viewModels<CurrencyDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyDetailBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book = args.id
        viewModel.start(book)
        setViewModelListeners()
    }

    private fun setOrders(orders: Map<String, List<Order>>) {
        val adapter = OrderPageAdapter(parentFragmentManager, lifecycle)
        adapter.setOrders(orders)

        val tabLayout = binding?.tlOrders
        val viewPager = binding?.vpOrders
        viewPager?.adapter = adapter
        if (tabLayout != null && viewPager != null)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    OrderType.ASKS.page -> tab.text = OrderType.ASKS.label
                    OrderType.BIDS.page -> tab.text = OrderType.BIDS.label
                }
            }.attach()
    }

    private fun setViewModelListeners() {
        viewModel.currencyDetail.observe(viewLifecycleOwner) {
            setCurrencyDetailInfo(it)
        }

        viewModel.openOrders.observe(viewLifecycleOwner) {
            setOrders(it)
        }

        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {
                is SendMessage -> showMessage(event.message)
                is ShowLoading -> binding?.loading?.root?.visibility =
                    if (event.show) View.VISIBLE else View.GONE
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }

    private fun setCurrencyDetailInfo(book: Book) {
        binding?.tvCurrencyDetail?.text = book.book
        binding?.tvVolume?.text = getString(R.string.volume, book.volume)
        binding?.tvHigh?.text = getString(R.string.high, book.high)
        binding?.tvLast?.text = getString(R.string.last, book.last)
        binding?.tvLow?.text = getString(R.string.low, book.low)
        binding?.tvVwap?.text = getString(R.string.vwap, book.vwap)
        binding?.tvAsk?.text = getString(R.string.ask, book.ask)
        binding?.tvBid?.text = getString(R.string.bid, book.bid)
    }
}