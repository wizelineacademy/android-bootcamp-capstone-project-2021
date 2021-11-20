package dev.ricsarabia.cryptochallenge.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ricsarabia.cryptochallenge.databinding.DetailFragmentBinding
import dev.ricsarabia.cryptochallenge.ui.MainViewModel
import dev.ricsarabia.cryptochallenge.utils.asDecimalPrice

class DetailFragment : Fragment() {
    private lateinit var binding: DetailFragmentBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }
    private val asksAdapter = OrdersAdapter()
    private val bidsAdapter = OrdersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Init components
        binding.asksLinearLayout.layoutManager = LinearLayoutManager(context)
        binding.asksLinearLayout.adapter = asksAdapter
        binding.bidsLinearLayout.layoutManager = LinearLayoutManager(context)
        binding.bidsLinearLayout.adapter = bidsAdapter

        // Init observers
        viewModel.selectedBookPrices.observe(viewLifecycleOwner, {
            binding.majorTextView.text = it.book.substringBefore("_").uppercase()
            binding.minorTextView.text = it.book.substringAfter("_").uppercase()
            binding.lastPriceTextView.text = it.last.asDecimalPrice()
            binding.higherPriceTextView.text = it.high.asDecimalPrice()
            binding.lowerPriceTextView.text = it.low.asDecimalPrice()
        })
        viewModel.selectedBookOrders.observe(viewLifecycleOwner, {
            asksAdapter.orders = it.asks
            bidsAdapter.orders = it.bids
        })
        viewModel.loading.observe(viewLifecycleOwner, {
            binding.detailProgressBar.isVisible = it
        })

        // Retrieve book details
        viewModel.getBookPrices()
        viewModel.getBookOrders()
    }
}